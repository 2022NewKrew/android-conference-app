package com.survivalcoding.ifkakao.presentation.detail.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SessionDescriptionFragment()
            1 -> SessionCommentFragment()
            else -> throw Exception()
        }
    }
}