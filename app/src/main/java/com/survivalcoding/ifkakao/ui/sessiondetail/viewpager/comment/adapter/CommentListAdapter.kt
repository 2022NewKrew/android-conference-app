package com.survivalcoding.ifkakao.ui.sessiondetail.viewpager.comment.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.ifkakao.ui.sessiondetail.viewpager.comment.Comment

class CommentListAdapter: ListAdapter<Comment, CommentViewHolder>(CommentDiffItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        return
    }
}