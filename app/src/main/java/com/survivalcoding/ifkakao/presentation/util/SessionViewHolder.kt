package com.survivalcoding.ifkakao.presentation.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.databinding.ItemSessionBinding
import com.survivalcoding.ifkakao.domain.model.Session

class SessionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemSessionBinding.bind(itemView)

    fun bind(session: Session) {
        binding.companyTag.text = session.companyName
        binding.fieldTag.text = session.field
        binding.titleText.text = session.title
        binding.videoTime.text = session.linkList.VIDEO[0].description

    }
}