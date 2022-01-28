package com.survivalcoding.ifkakao.presentation.commons

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.survivalcoding.ifkakao.databinding.ItemSessionBinding
import com.survivalcoding.ifkakao.domain.model.Session

class SessionViewHolder(
    itemView: View,
    private val onClickSession: (Int) -> (Unit),
) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemSessionBinding.bind(itemView)

    fun bind(session: Session) {
        binding.companyTag.text = session.companyName
        binding.fieldTag.text = session.field
        binding.titleText.text = session.title
        binding.videoTime.text = session.linkList?.VIDEO?.get(0)?.description ?: ""
        session.linkList?.PC_IMAGE?.get(0)?.url?.let {
            Glide.with(itemView)
                .load(it)
                .into(binding.thumbnailSiv)
        }
        itemView.setOnClickListener {
            onClickSession(session.idx)
        }
    }
}