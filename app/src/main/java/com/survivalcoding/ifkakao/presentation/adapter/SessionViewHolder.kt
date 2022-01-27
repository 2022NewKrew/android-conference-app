package com.survivalcoding.ifkakao.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.survivalcoding.ifkakao.databinding.ItemSessionBinding
import com.survivalcoding.ifkakao.domain.model.Session

class SessionViewHolder(
    private val binding: ItemSessionBinding,
    private val itemClickListener: (Session) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(session: Session) {
        Glide
            .with(binding.sessionIvThumbnail)
            .load(session.linkList.PC_IMAGE[0].url)
            .into(binding.sessionIvThumbnail)
        binding.sessionTvLength.text = session.linkList.VIDEO[0].description
        binding.sessionTvCompany.text = session.companyName
        binding.sessionTvCategory.text = session.field
        binding.sessionTvDescription.text = session.title

        binding.root.setOnClickListener { itemClickListener(session) }
    }
}