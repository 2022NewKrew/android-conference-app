package com.survivalcoding.ifkakao.presentation.detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.ifkakao.domain.model.IkSessionSpeaker

class SpeakerListAdapter : ListAdapter<IkSessionSpeaker, SpeakerListViewHolder>(object :
    DiffUtil.ItemCallback<IkSessionSpeaker>() {
    override fun areItemsTheSame(oldItem: IkSessionSpeaker, newItem: IkSessionSpeaker): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: IkSessionSpeaker, newItem: IkSessionSpeaker): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeakerListViewHolder {
        return SpeakerListViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SpeakerListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<IkSessionSpeaker>?) {
        super.submitList(list)
    }
}