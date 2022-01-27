package com.survivalcoding.ifkakao.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.ifkakao.databinding.ItemSessionBinding
import com.survivalcoding.ifkakao.domain.model.Session

class SessionListAdapter(
    private val itemClickListener: (Session) -> Unit
) : ListAdapter<Session, SessionViewHolder>(SessionDiffItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        return SessionViewHolder(
            ItemSessionBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}