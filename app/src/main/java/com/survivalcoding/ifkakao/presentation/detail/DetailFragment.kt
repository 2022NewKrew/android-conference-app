package com.survivalcoding.ifkakao.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentDetailBinding
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.presentation.adapter.SessionListAdapter
import com.survivalcoding.ifkakao.presentation.detail.viewpager.DetailPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()
    private val adapter by lazy {
        SessionListAdapter { session -> moveToDetail(session) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (arguments?.get("sessionId") as Int?)?.run { viewModel.getSessionById(this) }

        viewModel.session.observe(viewLifecycleOwner) { session ->
            // viewpager 설정
            binding.detailViewPager.adapter = DetailPagerAdapter(this, session)
            TabLayoutMediator(binding.detailTabLayout, binding.detailViewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "세션 설명"
                    1 -> "댓글"
                    else -> throw Exception()
                }
            }.attach()

            // web view 동영상 설정
            binding.detailWebView.settings.javaScriptEnabled = true
            binding.detailWebView.loadUrl(session.linkList.VIDEO[0].url)

            // 연관 세션 조회
            viewModel.getSessionsRelated(session.idx, session.field)

            // 좋아요 여부 조회
            viewModel.checkLike(session)
        }

        // 연관 세션 recyclerView 설정
        binding.detailRvRelation.adapter = adapter

        viewModel.relatedSessions.observe(this) { sessions ->
            adapter.submitList(sessions)
        }

        // 화면 상단으로 이동
        binding.detailFooter.footerIbUp.setOnClickListener { binding.detailScrollView.smoothScrollTo(0, 0) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun moveToDetail(session: Session?) {
        parentFragmentManager.commit {
            replace<DetailFragment>(
                R.id.fragment_container_view,
                args = bundleOf("sessionId" to session?.idx),
            )
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}