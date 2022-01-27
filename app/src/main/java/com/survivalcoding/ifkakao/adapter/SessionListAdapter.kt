package com.survivalcoding.ifkakao.adapter

import android.view.ViewGroup
import com.example.domain.entity.Data
import androidx.recyclerview.widget.ListAdapter


class SessionListAdapter(
    private val onClicked: (Data) -> Unit
) :
    ListAdapter<Data, SessionViewHolder>(SessionDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder =
        SessionViewHolder.builder(parent)

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item, onClicked)
        }
    }
}