package com.survivalcoding.ifkakao.presentation.sessions.adapter

import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.databinding.ItemFilterBinding

class FilterViewHolder(
    private val binding: ItemFilterBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(keyword: String) {
        binding.filterTvKeyword.text = keyword
    }
}