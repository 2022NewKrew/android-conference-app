package com.survivalcoding.ifkakao.presentation.detail.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.ifkakao.domain.model.ContentsSpeaker

object SpeakerDiffItemCallback : DiffUtil.ItemCallback<ContentsSpeaker>() {
    override fun areItemsTheSame(oldItem: ContentsSpeaker, newItem: ContentsSpeaker): Boolean {
        return oldItem.idx == newItem.idx
    }

    override fun areContentsTheSame(oldItem: ContentsSpeaker, newItem: ContentsSpeaker): Boolean {
        return oldItem == newItem
    }
}