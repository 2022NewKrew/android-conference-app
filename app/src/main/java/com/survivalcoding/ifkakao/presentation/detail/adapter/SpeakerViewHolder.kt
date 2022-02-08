package com.survivalcoding.ifkakao.presentation.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.survivalcoding.ifkakao.databinding.ItemSpeakerBinding
import com.survivalcoding.ifkakao.domain.model.ContentsSpeaker

class SpeakerViewHolder(
    private val binding: ItemSpeakerBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(speaker: ContentsSpeaker, imageUrl: String) {
        Glide
            .with(binding.speakerIvProfile)
            .load(imageUrl)
            .into(binding.speakerIvProfile)

        val name = "${speaker.nameKo} ${speaker.nameEn}"
        binding.speakerTvName.text = name
        binding.speakerTvCompany.text = speaker.company
        binding.speakerTvOccupation.text = speaker.occupation
    }
}