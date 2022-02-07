package com.survivalcoding.ifkakao.presentation.detail.adapter

import androidx.recyclerview.widget.DiffUtil

object ClassificationDiffItemCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}