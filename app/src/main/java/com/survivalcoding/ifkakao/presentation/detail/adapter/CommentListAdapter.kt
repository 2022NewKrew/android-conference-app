package com.survivalcoding.ifkakao.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.ifkakao.databinding.ItemCommentBinding
import com.survivalcoding.ifkakao.domain.model.Comment

class CommentListAdapter :
    ListAdapter<Comment, CommentViewHolder>(CommentDiffItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}