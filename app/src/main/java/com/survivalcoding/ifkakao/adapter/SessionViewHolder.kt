package com.survivalcoding.ifkakao.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Data
import com.survivalcoding.ifkakao.databinding.ItemSessionBinding

class SessionViewHolder(private val binding: ItemSessionBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(item: Data) {
        binding.sessionTitle.text = item.title
        binding.sessionCompany.text = item.company
        binding.sessionField.text = item.field

    }

    companion object {
        fun builder(parent: ViewGroup): SessionViewHolder = SessionViewHolder(
            ItemSessionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}