package com.survivalcoding.ifkakao.presentation.detail.viewpager

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.survivalcoding.ifkakao.domain.model.Session

class DetailPagerAdapter(fragment: Fragment, private val session: Session) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SessionDescriptionFragment().apply {
                arguments = bundleOf("session" to session)
            }
            1 -> SessionCommentFragment().apply {
                arguments = bundleOf("session" to session)
            }
            else -> throw Exception()
        }
    }
}