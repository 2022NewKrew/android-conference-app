package com.survivalcoding.ifkakao.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentDetailDescriptionBinding
import com.survivalcoding.ifkakao.presentation.base.BaseFragment
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

    private val currentSession = stk.peek().session

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind {
            tagAdapter = TagListAdapter {
                stk.push(
                    FragmentInformation(
                        fragmentType = FragmentType.KEYWORD,
                        selectedKeyword = it
                    )
                )
                parentFragmentManager.commit {
                    replace(R.id.fragment_container_view, KeywordFragment())
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }.apply { submitList(session?.tag) }

            speakerAdapter = SpeakerListAdapter().apply { submitList(session?.sessionSpeakers) }

            session = currentSession

            btnDetailToSessions.setOnClickListener {
                toAllSession()
                parentFragmentManager.commit {
                    replace(R.id.fragment_container_view, SessionFragment())
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
        }
    }

    private fun toAllSession() {
        stk.pop()
        stk.push(
            FragmentInformation(
                fragmentType = FragmentType.DETAIL,
                session = currentSession,
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