package com.survivalcoding.ifkakao.presentation.util

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.ifkakao.domain.model.IkSessionData

class SessionListAdapter(
    private val onClickListener: (session: IkSessionData) -> Unit,
) : ListAdapter<IkSessionData, SessionListViewHolder>(object :
    DiffUtil.ItemCallback<IkSessionData>() {
    override fun areItemsTheSame(oldItem: IkSessionData, newItem: IkSessionData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: IkSessionData, newItem: IkSessionData): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionListViewHolder {
        return SessionListViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SessionListViewHolder, position: Int) {
        holder.bind(
            session = getItem(position),
            onClickListener = onClickListener,
        )
    }

    override fun getItemViewType(position: Int): Int = 1
}