package com.survivalcoding.ifkakao.presentation.sessions.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.ifkakao.databinding.ItemFilterBinding

class FilterListAdapter :
    ListAdapter<String, FilterViewHolder>(FilterDiffItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(
            ItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}