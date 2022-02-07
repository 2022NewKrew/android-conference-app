package com.survivalcoding.ifkakao.presentation.detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.ifkakao.domain.model.IkComment

class CommentListAdapter :
    ListAdapter<IkComment, CommentListViewHolder>(object : DiffUtil.ItemCallback<IkComment>() {
        override fun areItemsTheSame(oldItem: IkComment, newItem: IkComment): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: IkComment, newItem: IkComment): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentListViewHolder {
        return CommentListViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CommentListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}