package com.survivalcoding.ifkakao.presentation.detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.ifkakao.domain.model.IkTagInfo

class TagListAdapter(
    private val onClickListener: (tag: IkTagInfo) -> Unit,
) : ListAdapter<IkTagInfo, TagListViewHolder>(object : DiffUtil.ItemCallback<IkTagInfo>() {
    override fun areItemsTheSame(oldItem: IkTagInfo, newItem: IkTagInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: IkTagInfo, newItem: IkTagInfo): Boolean {
        return oldItem == newItem
    }

}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagListViewHolder {
        return TagListViewHolder(parent)
    }

    override fun onBindViewHolder(holder: TagListViewHolder, position: Int) {
        holder.bind(getItem(position), onClickListener)
    }
}