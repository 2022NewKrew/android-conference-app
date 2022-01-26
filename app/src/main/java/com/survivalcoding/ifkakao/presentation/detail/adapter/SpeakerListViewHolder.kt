package com.survivalcoding.ifkakao.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.SpeakerListItemBinding
import com.survivalcoding.ifkakao.domain.model.IkSessionSpeaker

class SpeakerListViewHolder(val parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.speaker_list_item, parent, false)
) {
    private val binding = SpeakerListItemBinding.bind(itemView)

    fun bind(speaker: IkSessionSpeaker) {
        Glide.with(parent.context)
            .load(speaker.imageUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(70)))
            .into(binding.speakerImage)

        binding.tvSpeakerName.text = speaker.name
        binding.tvSpeakerCompany.apply {
            if (speaker.company == null) isVisible = false
            else {
                isVisible = true
                text = speaker.company
            }
        }
        binding.tvSpeakerOccupation.apply {
            if (speaker.occupation == null) isVisible = false
            else {
                isVisible = true
                text = speaker.occupation
            }
        }
    }
}