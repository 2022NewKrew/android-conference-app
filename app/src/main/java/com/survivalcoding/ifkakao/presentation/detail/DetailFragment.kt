package com.survivalcoding.ifkakao.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentDetailBinding
import com.survivalcoding.ifkakao.presentation.base.BaseFragment
import com.survivalcoding.ifkakao.presentation.day.SessionFragment
import com.survivalcoding.ifkakao.presentation.detail.adapter.SpeakerListAdapter
import com.survivalcoding.ifkakao.presentation.detail.adapter.TagListAdapter
import com.survivalcoding.ifkakao.presentation.keyword.KeywordFragment
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import com.survivalcoding.ifkakao.presentation.util.FragmentType
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

    private val tagListAdapter = TagListAdapter(
        onClickListener = {
            stk.push(FragmentInformation(fragmentType = FragmentType.KEYWORD, selectedKeyword = it))
            parentFragmentManager.commit {
                replace(R.id.fragment_container_view, KeywordFragment())
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    )

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

    private val speakerListAdapter = SpeakerListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind {
            tagAdapter = tagListAdapter
            speakerAdapter = speakerListAdapter
            sessionAdapter = sessionListAdapter
            itemDecoration = SessionItemDecoration()
            executePendingBindings()
            vm = viewModel

            btnMoreSessions.setOnClickListener {
                viewModel.onEvent(DetailEvent.LoadMoreSessions)
            }

            btnDetailToSessions.setOnClickListener {
                viewModel.onEvent(DetailEvent.ToAllSession)
                parentFragmentManager.commit {
                    replace(R.id.fragment_container_view, SessionFragment())
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
        }

        viewModel.sessions.observe(viewLifecycleOwner) {
            sessionListAdapter.submitList(it)
        }
        viewModel.currentSession.observe(viewLifecycleOwner) {
            tagListAdapter.submitList(it.tag)
            speakerListAdapter.submitList(it.sessionSpeakers)
        }
        viewModel.exposedListCount.observe(viewLifecycleOwner) {
            bind { btnMoreSessions.isVisible = viewModel.totalCount > it }
        }
    }
}