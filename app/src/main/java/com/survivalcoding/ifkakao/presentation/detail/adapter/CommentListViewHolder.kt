package com.survivalcoding.ifkakao.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ListItemCommentBinding
import com.survivalcoding.ifkakao.domain.model.IkComment

class CommentListViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.list_item_comment, parent, false)
) {
    private val binding = ListItemCommentBinding.bind(itemView)

    fun bind(comment: IkComment) {
        binding.comment = comment
    }
}