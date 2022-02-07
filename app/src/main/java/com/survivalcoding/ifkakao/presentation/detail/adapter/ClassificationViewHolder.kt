package com.survivalcoding.ifkakao.presentation.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.databinding.ItemClassificationBinding

class ClassificationViewHolder(
    private val binding: ItemClassificationBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(classification: String) {
        binding.classificationTvName.text = classification
    }
}