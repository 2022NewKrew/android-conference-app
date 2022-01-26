package com.survivalcoding.ifkakao.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ListItemSessionBinding
import com.survivalcoding.ifkakao.domain.model.Data

class SessionViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.list_item_session, parent, false
    )
) {

    private val binding = ListItemSessionBinding.bind(itemView)

    fun bind(session: Data) {
        Glide.with(itemView).load(session.linkList.PC_IMAGE.first().url).into(binding.thumbnailSiv)
        binding.timeTv.setText(session.linkList.VIDEO.first().description)
        binding.companyTv.setText(session.companyName)
        binding.fieldTv.setText(session.field)
        binding.titleTv.setText(session.title)
    }
}