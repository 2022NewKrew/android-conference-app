package com.survivalcoding.ifkakao.ui.sessiondetail.viewpager.detail

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.survivalcoding.ifkakao.domain.model.Data
import com.survivalcoding.ifkakao.ui.sessiondetail.viewpager.comment.SessionCommentFragment
import java.lang.IllegalArgumentException

class SessionDetailFragmentStateAdapter(fragment: Fragment, private val session: Data): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                SessionDescriptionFragment().apply {
                    arguments = bundleOf(SessionDescriptionFragment.ARG_KEY_SESSION to session)
                }
            }
            1 -> {
                SessionCommentFragment().apply {
                    arguments = bundleOf(SessionCommentFragment.ARG_KEY_SESSION to session)
                }
            }
            else -> {
                throw IllegalArgumentException("Only 0 until ${itemCount - 1} is allowed but $position was given!")
            }
        }
    }
}