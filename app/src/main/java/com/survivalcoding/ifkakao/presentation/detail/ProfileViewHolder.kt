package com.survivalcoding.ifkakao.presentation.detail

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.survivalcoding.ifkakao.databinding.ItemDetailProfileBinding
import com.survivalcoding.ifkakao.domain.model.ProfileInfo

class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemDetailProfileBinding.bind(itemView)

    fun bind(profile: ProfileInfo) {
        binding.profileName.text = profile.nameEn + " " + profile.nameKo
        binding.profileCompany.text = profile.company
        binding.profileTeam.text = profile.occupation
        binding.talkChannelButton.isVisible = profile.channelLink != null

        Glide.with(itemView)
            .load(profile.url)
            .into(binding.imageView)
    }

}