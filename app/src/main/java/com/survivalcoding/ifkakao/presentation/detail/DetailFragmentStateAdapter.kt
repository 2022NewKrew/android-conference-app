package com.survivalcoding.ifkakao.presentation.detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailFragmentStateAdapter(
    fragment: Fragment,
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DetailDescriptionFragment()
            1 -> DetailCommentFragment()
            else -> throw IllegalArgumentException("view pager error")
        }
    }
}