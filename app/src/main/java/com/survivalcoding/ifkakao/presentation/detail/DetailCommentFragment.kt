package com.survivalcoding.ifkakao.presentation.detail

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentDetailCommentBinding
import com.survivalcoding.ifkakao.presentation.commons.FooterAdapter
import com.survivalcoding.ifkakao.presentation.commons.SessionAdapter
import com.survivalcoding.ifkakao.presentation.main.MainFragment
import com.survivalcoding.ifkakao.sharedViewModel

class DetailCommentFragment : Fragment() {
    private val detailViewModel: DetailViewModel by sharedViewModel<DetailViewModel, DetailFragment>()

    private var _binding: FragmentDetailCommentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerView
        recyclerView.isNestedScrollingEnabled =
            resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        val concatAdapter = ConcatAdapter(
            CommentAdapter(),
            SessionAdapter { idx ->
                moveToNextFragment(DetailFragment().apply {
                    this.arguments = bundleOf(MainFragment.SELECTED to idx)
                })
            },
            FooterAdapter(
                onClickUpButton = {
                    if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                        recyclerView.smoothScrollToPosition(0)
                    else requireParentFragment().requireView()
                        .findViewById<NestedScrollView>(R.id.scrollView).smoothScrollTo(0, 0)
                }, onClickSite = {
                    val browserIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://if.kakao.com/2020/")
                    )
                    if (browserIntent.resolveActivity(requireActivity().packageManager) != null) {
                        startActivity(browserIntent)
                    }
                }
            )
        )

        recyclerView.adapter = concatAdapter

        detailViewModel.relatedSessions.observe(viewLifecycleOwner) {
            (concatAdapter.adapters[1] as SessionAdapter).submitList(it)
        }
    }

    private fun moveToNextFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainerView,
                fragment
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}