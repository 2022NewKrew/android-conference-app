package com.survivalcoding.ifkakao.ui.sessiondetail.viewpager.comment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ListItemCommentBinding

class CommentViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.list_item_comment, parent, false)
) {

    private val binding = ListItemCommentBinding.bind(itemView)
}