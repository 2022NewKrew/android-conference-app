package com.survivalcoding.ifkakao.presentation.detail.subtab.descriptiontab

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentDetailDescriptionBinding
import com.survivalcoding.ifkakao.presentation.base.BaseFragment
import com.survivalcoding.ifkakao.presentation.detail.DetailEvent
import com.survivalcoding.ifkakao.presentation.detail.DetailViewModel
import com.survivalcoding.ifkakao.presentation.detail.adapter.SpeakerListAdapter
import com.survivalcoding.ifkakao.presentation.detail.adapter.TagListAdapter
import com.survivalcoding.ifkakao.presentation.keyword.KeywordFragment
import com.survivalcoding.ifkakao.presentation.session.SessionFragment
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import com.survivalcoding.ifkakao.presentation.util.FragmentType
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class DetailDescriptionFragment :
    BaseFragment<FragmentDetailDescriptionBinding>(R.layout.fragment_detail_description) {

    @Inject
    lateinit var stk: Stack<FragmentInformation>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val speakerListAdapter = SpeakerListAdapter()
        val tagListAdapter = TagListAdapter(
            onClickListener = {
                stk.push(
                    FragmentInformation(
                        fragmentType = FragmentType.KEYWORD,
                        selectedKeyword = it
                    )
                )
                parentFragment?.parentFragmentManager?.commit {
                    replace(R.id.fragment_container_view, KeywordFragment())
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
        )

        val viewModel = parentFragment?.let { parent ->
            ViewModelProvider(parent)[DetailViewModel::class.java]
        }

        bind {
            tagAdapter = tagListAdapter

            speakerList.adapter = speakerListAdapter

            executePendingBindings()

            session = stk.peek().session

            btnDetailToSessions.setOnClickListener {
                toAllSession()
                parentFragment?.parentFragmentManager?.commit {
                    replace(R.id.fragment_container_view, SessionFragment())
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }

            ivLikeButton.setOnClickListener {
                viewModel?.onEvent(DetailEvent.ToggleLiked)
            }
        }

        viewModel?.currentSession?.observe(viewLifecycleOwner) {
            speakerListAdapter.submitList(it.sessionSpeakers)
            tagListAdapter.submitList(it.tag)
        }
        viewModel?.localSessionData?.observe(viewLifecycleOwner) {
            bind {
                ivLikeButton.imageTintList = when (it.isLiked) {
                    true -> ColorStateList.valueOf(Color.parseColor("#ffe812"))
                    else -> ColorStateList.valueOf(Color.parseColor("#ffffff"))
                }
            }
        }
    }

    private fun toAllSession() {
        val top = stk.pop()
        stk.push(
            FragmentInformation(
                fragmentType = FragmentType.DETAIL,
                session = top.session,
            )
        )
        stk.push(
            FragmentInformation(
                fragmentType = FragmentType.SESSION,
                exposedListCount = 8,
                selectedDay = 3
            )
        )
    }
}