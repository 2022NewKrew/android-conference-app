package com.survivalcoding.ifkakao.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val fragments: List<Fragment> = listOf(DayOneFragment(), DayTwoFragment(), DayThreeFragment())

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}