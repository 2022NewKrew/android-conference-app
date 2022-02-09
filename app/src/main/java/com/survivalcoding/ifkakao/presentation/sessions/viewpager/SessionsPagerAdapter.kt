package com.survivalcoding.ifkakao.presentation.sessions.viewpager

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SessionsPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SessionsDayFragment().apply {
                arguments = bundleOf("day" to "1Day")
            }
            1 -> SessionsDayFragment().apply {
                arguments = bundleOf("day" to "2Day")
            }
            2 -> SessionsDayFragment().apply {
                arguments = bundleOf("day" to "All")
            }
            else -> throw Exception()
        }
    }
}