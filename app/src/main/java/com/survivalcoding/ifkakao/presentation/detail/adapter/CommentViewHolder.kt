package com.survivalcoding.ifkakao.presentation.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.databinding.ItemCommentBinding
import com.survivalcoding.ifkakao.domain.model.Comment

class CommentViewHolder(
    private val binding: ItemCommentBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(comment: Comment) {
        binding.commentTvNickname.text = comment.nickname
        binding.commentTvContent.text = comment.content
        val replyCount = "답글${comment.replyCount}"
        binding.commentTvReply.text = replyCount
    }
}