package com.survivalcoding.ifkakao.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentDetailBinding
import com.survivalcoding.ifkakao.domain.model.IkSessionData
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

    private var player: SimpleExoPlayer? = null

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

        bind {
            sessionAdapter = sessionListAdapter
            itemDecoration = SessionItemDecoration()
            executePendingBindings()
            vm = viewModel

            btnMoreSessions.setOnClickListener {
                viewModel.onEvent(DetailEvent.LoadMoreSessions)
            }

            scrollTopButton.setOnClickListener { backgroundNestedScrollView.smoothScrollTo(0, 0) }
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