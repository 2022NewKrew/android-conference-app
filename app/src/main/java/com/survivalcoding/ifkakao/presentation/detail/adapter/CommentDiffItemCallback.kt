package com.survivalcoding.ifkakao.presentation.detail.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.ifkakao.domain.model.Comment

object CommentDiffItemCallback : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.idx == newItem.idx
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}