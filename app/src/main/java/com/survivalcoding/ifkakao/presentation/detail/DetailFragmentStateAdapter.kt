package com.survivalcoding.ifkakao.presentation.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailFragmentStateAdapter(
    fragment: Fragment,
    private val fragmentManager: FragmentManager,
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DetailDescriptionFragment(fragmentManager)
            1 -> DetailCommentFragment(fragmentManager)
            else -> throw IllegalArgumentException("view pager error")
        }
    }
}