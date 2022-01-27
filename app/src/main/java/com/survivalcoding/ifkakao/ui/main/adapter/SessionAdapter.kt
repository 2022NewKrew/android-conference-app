package com.survivalcoding.ifkakao.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.ifkakao.domain.model.Data

class SessionAdapter(private val onClick: (Data) -> Unit): ListAdapter<Data, SessionViewHolder>(SessionDiffItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        return SessionViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        val session = getItem(position)
        holder.bind(session)
        holder.itemView.setOnClickListener { onClick(session) }
    }
}