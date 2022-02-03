package com.survivalcoding.ifkakao.presentation.detail.subtab.descriptiontab

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
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
class DetailDescriptionFragment(
    private val mFragmentManager: FragmentManager,
) :
    BaseFragment<FragmentDetailDescriptionBinding>(R.layout.fragment_detail_description) {

    @Inject
    lateinit var stk: Stack<FragmentInformation>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentSession = stk.peek().session

        val speakerListAdapter = SpeakerListAdapter()

        bind {
            tagAdapter = TagListAdapter(
                onClickListener = {
                    stk.push(
                        FragmentInformation(
                            fragmentType = FragmentType.KEYWORD,
                            selectedKeyword = it
                        )
                    )
                    mFragmentManager.commit {
                        replace(R.id.fragment_container_view, KeywordFragment())
                        setReorderingAllowed(true)
                        addToBackStack(null)
                    }
                }
            ).apply { submitList(currentSession.tag) }

            speakerList.adapter = speakerListAdapter

            executePendingBindings()

            session = currentSession

            btnDetailToSessions.setOnClickListener {
                toAllSession()
                mFragmentManager.commit {
                    replace(R.id.fragment_container_view, SessionFragment())
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
        }

        speakerListAdapter.submitList(currentSession.sessionSpeakers.toMutableList())
    }

    private fun toAllSession() {
        stk.pop()
        stk.push(
            FragmentInformation(
                fragmentType = FragmentType.DETAIL,
                session = stk.peek().session,
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