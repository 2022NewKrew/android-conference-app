package com.survivalcoding.ifkakao.adapter

import android.view.ViewGroup
import com.example.domain.entity.Data
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.ifkakao.MainViewModel


class SessionListAdapter(
    private val onSessionClicked: (Data) -> Unit,
    private val onLikeClicked: (Int, Boolean) -> Unit,
    private val viewModel: MainViewModel? = null
) :
    ListAdapter<Data, SessionViewHolder>(SessionDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder =
        SessionViewHolder.builder(parent, viewModel)

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item, onSessionClicked, onLikeClicked)
        }
    }
}