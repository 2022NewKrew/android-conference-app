package com.survivalcoding.ifkakao.presentation.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.survivalcoding.ifkakao.databinding.ItemSessionBinding
import com.survivalcoding.ifkakao.databinding.ItemSpeakerBinding
import com.survivalcoding.ifkakao.domain.model.ContentsSpeaker
import com.survivalcoding.ifkakao.domain.model.Session

class SpeakerViewHolder(
    private val binding: ItemSpeakerBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(speaker: ContentsSpeaker) {
//        Glide
//            .with(binding.speakerIvProfile)
//            .load(speaker.)
//            .into(binding.speakerIvProfile)

        val name = "${speaker.nameKo} ${speaker.nameEn}"
        binding.speakerTvName.text = name
        binding.speakerTvCompany.text = speaker.company
        binding.speakerTvOccupation.text = speaker.occupation
    }
}