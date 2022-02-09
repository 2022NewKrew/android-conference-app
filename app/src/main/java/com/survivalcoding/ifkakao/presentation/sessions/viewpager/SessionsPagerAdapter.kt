package com.survivalcoding.ifkakao.presentation.sessions.viewpager

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.survivalcoding.ifkakao.domain.model.DayType

class SessionsPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SessionsDayFragment().apply {
                arguments = bundleOf("day" to DayType.Day1)
            }
            1 -> SessionsDayFragment().apply {
                arguments = bundleOf("day" to DayType.Day2)
            }
            2 -> SessionsDayFragment().apply {
                arguments = bundleOf("day" to DayType.Day3)
            }
            else -> throw Exception()
        }
    }
}