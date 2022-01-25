package com.survivalcoding.ifkakao.presentation.highlight.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.ifkakao.domain.model.IkSessionData

class HighlightListAdapter(
    private val clickListener: (session: IkSessionData) -> Unit,
) : ListAdapter<IkSessionData, HighlightListViewHolder>(object :
    DiffUtil.ItemCallback<IkSessionData>() {
    override fun areItemsTheSame(oldItem: IkSessionData, newItem: IkSessionData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: IkSessionData, newItem: IkSessionData): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightListViewHolder {
        return HighlightListViewHolder(parent)
    }

    override fun onBindViewHolder(holder: HighlightListViewHolder, position: Int) {
        holder.bind(
            session = getItem(position),
            clickListener = clickListener,
        )
    }
}