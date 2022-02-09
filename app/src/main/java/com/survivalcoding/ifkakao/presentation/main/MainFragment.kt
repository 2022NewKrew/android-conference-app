package com.survivalcoding.ifkakao.presentation.main

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentMainBinding
import com.survivalcoding.ifkakao.domain.entity.Session
import com.survivalcoding.ifkakao.presentation.common.*
import com.survivalcoding.ifkakao.presentation.session.SessionFragment
import com.survivalcoding.ifkakao.presentation.sessiondetail.SessionDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null

    private val viewModel: MainViewModel by viewModels()

    private val adapter: CommonAdapter by lazy {
        CommonAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSessionListRecyclerView()
        initBanner()
        initAllSessionButton()

        observe()
    }

    private fun initAllSessionButton() {
        binding?.allSessionButton?.setOnClickListener {
            navigateToSession()
        }
    }

    private fun navigateToSession() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, SessionFragment())
            .addToBackStack(SessionFragment.TAG)
            .commit()
    }

    private fun observe() {
        repeatOnStart {
            viewModel.highlightSessions.collectLatest {
                val binders: MutableList<CommonBinder> =
                    it.map { session -> SessionListItemBinder(session, ::navigateToSessionDetail) }
                        .toMutableList<CommonBinder>()
                        .apply {
                            add(FooterBinder())
                        }
                adapter.submitList(binders) {
                    scrollToTopOfTheList()
                }

            }
        }
    }

    private fun navigateToSessionDetail(session: Session) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, SessionDetailFragment.newInstance(session.idx))
            .addToBackStack(null)
            .commit()
    }

    private fun scrollToTopOfTheList() {
        binding?.sessionListRecyclerView?.scrollToPosition(0)
    }

    private fun initBanner() {
        Glide.with(requireContext())
            .load(getString(R.string.main_banner_image_url))
            .into(binding?.mainBannerImageView ?: return)


        if (Build.VERSION.SDK_INT >= 31) {
            binding!!.mainBannerImageView.setRenderEffect(
                RenderEffect.createBlurEffect(
                    10f,
                    10f,
                    Shader.TileMode.CLAMP
                )
            )
        }

        Glide.with(requireContext())
            .asGif()
            .load(getString(R.string.main_hand_gif_url))
            .into(binding?.handImageView ?: return)
    }

    private fun initSessionListRecyclerView() {
        binding?.sessionListRecyclerView?.adapter = adapter
        binding?.sessionListRecyclerView?.addItemDecoration(StickyFooterItemDecoration())
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
}