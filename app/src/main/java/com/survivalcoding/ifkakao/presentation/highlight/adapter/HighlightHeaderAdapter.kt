package com.survivalcoding.ifkakao.presentation.highlight.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.HighlightHeaderBinding
import com.survivalcoding.ifkakao.presentation.util.ImageResource

class HighlightHeaderAdapter(
    private val sessionButtonClickListener: () -> Unit,
) : RecyclerView.Adapter<HighlightHeaderAdapter.HeaderViewHolder>() {

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = HighlightHeaderBinding.bind(itemView)
        fun bind() {
            binding.imageResource = ImageResource()
            binding.allSessionButton.setOnClickListener {
                sessionButtonClickListener()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeaderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.highlight_header, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 1
}