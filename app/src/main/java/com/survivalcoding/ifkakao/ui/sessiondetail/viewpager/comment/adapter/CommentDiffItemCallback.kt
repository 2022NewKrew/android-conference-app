package com.survivalcoding.ifkakao.ui.sessiondetail.viewpager.comment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.ifkakao.ui.sessiondetail.viewpager.comment.Comment

object CommentDiffItemCallback: DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}