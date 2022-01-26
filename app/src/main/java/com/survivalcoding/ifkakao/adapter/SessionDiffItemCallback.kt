package com.survivalcoding.ifkakao.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entity.Data

object SessionDiffItemCallback : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.idx == newItem.idx
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }
}