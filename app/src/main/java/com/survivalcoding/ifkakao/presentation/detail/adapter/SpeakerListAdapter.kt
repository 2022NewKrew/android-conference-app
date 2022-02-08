package com.survivalcoding.ifkakao.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.ifkakao.databinding.ItemSpeakerBinding
import com.survivalcoding.ifkakao.domain.model.ContentsSpeaker
import com.survivalcoding.ifkakao.domain.model.File

class SpeakerListAdapter(private val profileImages: List<File>) :
    ListAdapter<ContentsSpeaker, SpeakerViewHolder>(SpeakerDiffItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeakerViewHolder {
        return SpeakerViewHolder(
            ItemSpeakerBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(holder: SpeakerViewHolder, position: Int) {
        return holder.bind(getItem(position), profileImages[position].url)
    }
}