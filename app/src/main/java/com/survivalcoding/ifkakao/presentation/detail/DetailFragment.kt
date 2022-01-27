package com.survivalcoding.ifkakao.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentDetailBinding
import com.survivalcoding.ifkakao.presentation.FragmentInformation
import com.survivalcoding.ifkakao.presentation.base.BaseFragment
import com.survivalcoding.ifkakao.presentation.detail.adapter.SpeakerListAdapter
import com.survivalcoding.ifkakao.presentation.detail.adapter.TagListAdapter
import com.survivalcoding.ifkakao.presentation.keyword.KeywordFragment
import com.survivalcoding.ifkakao.presentation.util.SessionItemDecoration
import com.survivalcoding.ifkakao.presentation.util.SessionListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    @Inject
    lateinit var stk: Stack<FragmentInformation>

    @Inject
    lateinit var sessionItemDecoration: SessionItemDecoration

    private val viewModel: DetailViewModel by viewModels()

    private val tagListAdapter = TagListAdapter(
        onClickListener = {
            stk.push(FragmentInformation(selectedKeyword = it))
            parentFragmentManager.commit {
                replace(R.id.fragment_container_view, KeywordFragment())
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    )

    private val sessionListAdapter = SessionListAdapter(
        onClickListener = {
            val top = stk.pop()
            stk.push(top.copy(relatedSessionsCount = viewModel.getCount()))
            stk.push(FragmentInformation(currentSession = it))
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
        viewModel.onEvent(DetailEvent.LoadingData(stk.peek()))

        bind {
            vm = viewModel
            tagAdapter = tagListAdapter
            speakerAdapter = speakerListAdapter
            sessionAdapter = sessionListAdapter
            itemDecoration = sessionItemDecoration

            btnMoreSessions.setOnClickListener { viewModel.onEvent(DetailEvent.LoadMoreSessions) }
        }
    }
}