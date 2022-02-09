package com.survivalcoding.ifkakao.presentation.sessiondetail

import android.app.DownloadManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.appbar.AppBarLayout
import com.kakao.sdk.link.LinkClient
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentSessionDetailBinding
import com.survivalcoding.ifkakao.domain.entity.Categories
import com.survivalcoding.ifkakao.presentation.MainActivity
import com.survivalcoding.ifkakao.presentation.categorySession.CategorySessionFragment
import com.survivalcoding.ifkakao.presentation.common.CommonAdapter
import com.survivalcoding.ifkakao.presentation.common.StickyFooterItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SessionDetailFragment : Fragment() {

    private var binding: FragmentSessionDetailBinding? = null

    private val viewModel: SessionDetailViewModel by viewModels()

    private val adapter: CommonAdapter by lazy {
        CommonAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            val sessionId = arguments?.getInt(SESSION_ID)
            if (sessionId != null) viewModel.setSessionId(sessionId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSessionDetailBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.addItemDecoration(StickyFooterItemDecoration())
        binding?.videoWebView?.settings?.javaScriptEnabled = true

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val appbarLayoutParams: AppBarLayout.LayoutParams =  binding?.videoConstraintLayout?.layoutParams as AppBarLayout.LayoutParams
            appbarLayoutParams.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
        }

        observe()
    }

    private fun observe() {
        repeatOnStart {
            viewModel.uiState.collectLatest {
                adapter.submitList(it.binderList)
                if (binding?.videoWebView?.url != it.video.url) {
                    binding?.videoWebView?.loadUrl(it.video.url)
                }
            }
        }

        repeatOnStart {
            viewModel.eventFlow.collect {
                handleEvent(it)
            }
        }
    }

    private fun handleEvent(event: SessionDetailViewModel.Event) {
        when (event) {
            is SessionDetailViewModel.Event.NavigateToWebView -> navigateToWebView(event.url)
            is SessionDetailViewModel.Event.NavigateToSessionList -> navigateToSessionList()
            is SessionDetailViewModel.Event.NavigateToSessionDetail -> navigateToSessionDetail(event.sessionId)
            is SessionDetailViewModel.Event.NavigateToCategorySessionList -> navigateToCategorySessionList(
                event.categories,
                event.title
            )
            is SessionDetailViewModel.Event.ShareSessionWithTalk -> shareSessionUrlWithTalk(event.sessionIdx)
            is SessionDetailViewModel.Event.ShareSession -> shareSession(event.sessionIdx)
            is SessionDetailViewModel.Event.CopySessionLink -> copySessionLink(event.sessionIdx)
            is SessionDetailViewModel.Event.DownloadFile -> downloadFile(
                event.uri,
                event.description
            )
        }
    }

    private fun shareSession(sessionIdx: Int) {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/pain"
            putExtra(Intent.EXTRA_TEXT, getString(R.string.session_url, sessionIdx))
            startActivity(Intent.createChooser(this, "세션 공유하기"))
        }
    }

    private fun shareSessionUrlWithTalk(sessionIdx: Int) {
        if (LinkClient.instance.isKakaoLinkAvailable(requireContext())) {
            // 카카오톡으로 카카오링크 공유 가능
            LinkClient.instance.scrapTemplate(
                requireContext(),
                getString(R.string.session_url, sessionIdx)
            ) { linkResult, error ->
                if (error != null) {
                    Log.e(TAG, "카카오링크 보내기 실패", error)
                } else if (linkResult != null) {
                    Log.d(TAG, "카카오링크 보내기 성공 ${linkResult.intent}")
                    startActivity(linkResult.intent)

                    // 카카오링크 보내기에 성공했지만 아래 경고 메시지가 존재할 경우 일부 컨텐츠가 정상 동작하지 않을 수 있습니다.
                    Log.w(TAG, "Warning Msg: ${linkResult.warningMsg}")
                    Log.w(TAG, "Argument Msg: ${linkResult.argumentMsg}")
                }
            }
        }
    }

    private fun copySessionLink(sessionIdx: Int) {
        val clipboard =
            requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("If Kakao", getString(R.string.session_url, sessionIdx))
        clipboard.setPrimaryClip(clip)
        Toast.makeText(requireContext(), "URL이 복사되었습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToWebView(url: String) {
        Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            if (resolveActivity(requireActivity().packageManager) != null) {
                requireActivity().startActivity(this)
            }
        }
    }

    private fun navigateToSessionList() {
        (requireActivity() as MainActivity).navigateToSessionList()
    }

    private fun navigateToSessionDetail(sessionId: Int) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, SessionDetailFragment.newInstance(sessionId))
            .addToBackStack(null)
            .commit()
    }

    private fun navigateToCategorySessionList(categories: Categories, title: String) {
        parentFragmentManager.beginTransaction().replace(
            R.id.fragment_container_view,
            CategorySessionFragment.newInstance(categories, title)
        ).addToBackStack(null)
            .commit()
    }

    private fun downloadFile(uri: String, description: String) {
        AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
            .setTitle(R.string.download)
            .setMessage(description)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(R.string.yes) { _, _ -> executeDownload(uri, description) }
            .show()
    }

    private fun executeDownload(uri: String, description: String) {
        val downloadRequest = DownloadManager.Request(Uri.parse(uri))
            .setTitle(description)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        (requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager).enqueue(
            downloadRequest
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun repeatOnStart(block: suspend CoroutineScope.() -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED, block)
        }
    }

    companion object {
        private const val SESSION_ID = "SESSION_ID"
        private val TAG = SessionDetailFragment::class.simpleName

        @JvmStatic
        fun newInstance(id: Int) =
            SessionDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(SESSION_ID, id)
                }
            }
    }
}