package com.survivalcoding.ifkakao.presentation.sessions.adapter

import androidx.recyclerview.widget.DiffUtil

object FilterDiffItemCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}