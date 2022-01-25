package com.survivalcoding.ifkakao.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.ifkakao.domain.model.Data

object SessionDiffItemCallback: DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.idx == newItem.idx
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }
}