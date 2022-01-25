package com.survivalcoding.ifkakao.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ItemMainFooterBinding

class FooterAdapter(private val onClickUpButton: (Unit) -> Unit) :
    RecyclerView.Adapter<FooterAdapter.FooterViewHolder>() {
    inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemMainFooterBinding = ItemMainFooterBinding.bind(itemView)
        fun bind() {
            binding.upButton.setOnClickListener {
                onClickUpButton(Unit)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FooterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_footer, parent, false)
        return FooterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FooterAdapter.FooterViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 1
}