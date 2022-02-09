package com.survivalcoding.ifkakao.presentation.sessions

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ItemSessionsHeaderBinding

class SessionsHeaderViewHolder(
    itemView: View,
    private val onClickTab: (Int) -> Unit,
    private val onClickFilter: () -> Unit,
) : RecyclerView.ViewHolder(itemView) {
    private val binding: ItemSessionsHeaderBinding = ItemSessionsHeaderBinding.bind(itemView)
    fun bind(idx: Int) {
        Glide.with(itemView).load(R.raw.vod_teaser_2021_mobile).into(binding.videoGif)

        val tabLayout = binding.tabLayout
        tabLayout.clearOnTabSelectedListeners()
        tabLayout.getTabAt(idx)?.select()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { onClickTab(it.position) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        binding.filterButton.setOnClickListener {
            onClickFilter()
        }
    }

}