package com.survivalcoding.ifkakao.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentDetailBinding
import com.survivalcoding.ifkakao.presentation.base.BaseFragment
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import com.survivalcoding.ifkakao.presentation.util.SessionItemDecoration
import com.survivalcoding.ifkakao.presentation.util.SessionListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    @Inject
    lateinit var stk: Stack<FragmentInformation>

    private val viewModel: DetailViewModel by viewModels()

    private val sessionListAdapter = SessionListAdapter(
        onClickListener = {
            viewModel.onEvent(DetailEvent.NextSession(it))
            parentFragmentManager.commit {
                replace(R.id.fragment_container_view, DetailFragment())
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = DetailFragmentStateAdapter(this, parentFragmentManager)

        bind {
            sessionAdapter = sessionListAdapter
            itemDecoration = SessionItemDecoration()
            executePendingBindings()
            vm = viewModel

            viewPager.adapter = pagerAdapter
            ViewPager2ViewHeightAnimator().viewPager2 = viewPager

            btnMoreSessions.setOnClickListener {
                viewModel.onEvent(DetailEvent.LoadMoreSessions)
            }

            scrollTopButton.setOnClickListener { backgroundNestedScrollView.smoothScrollTo(0, 0) }

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "세션 설명"
                    1 -> "댓글"
                    else -> throw IllegalArgumentException("View pager error")
                }
            }.attach()
        }

        viewModel.sessions.observe(viewLifecycleOwner) {
            sessionListAdapter.submitList(it)
        }

        viewModel.currentSession.observe(viewLifecycleOwner) {

        }

        viewModel.exposedListCount.observe(viewLifecycleOwner) {
            bind { btnMoreSessions.isVisible = viewModel.totalCount > it }
        }
    }
}