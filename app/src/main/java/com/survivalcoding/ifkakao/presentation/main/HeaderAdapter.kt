package com.survivalcoding.ifkakao.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ItemMainHeaderBinding

class HeaderAdapter(private val onClickSessionButton: (Unit) -> Unit) :
    RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemMainHeaderBinding = ItemMainHeaderBinding.bind(itemView)
        fun bind() {
            Glide.with(itemView).load(R.raw.ico_bye_2021).into(binding.byeGif)

            binding.sessionButton.setOnClickListener {
                onClickSessionButton(Unit)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_header, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 1
}