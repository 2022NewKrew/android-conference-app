package com.survivalcoding.ifkakao.presentation.sessions.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.ifkakao.databinding.ItemFilterBinding

class FilterListAdapter(
    private val selectedKeywords: MutableList<String>,
    private val itemClickListener: (String) -> Unit
) :
    ListAdapter<String, FilterViewHolder>(FilterDiffItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(
            ItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        return holder.bind(getItem(position), selectedKeywords.contains(getItem(position)))
    }
}