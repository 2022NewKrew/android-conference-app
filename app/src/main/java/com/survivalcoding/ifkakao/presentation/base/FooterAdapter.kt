package com.survivalcoding.ifkakao.presentation.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FooterBinding

class FooterAdapter(
    private val topButtonClickListener: () -> Unit,
) : RecyclerView.Adapter<FooterAdapter.FooterViewHolder>() {

    inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = FooterBinding.bind(itemView)
        fun bind() {
            binding.scrollTopButton.setOnClickListener {
                topButtonClickListener()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FooterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.footer, parent, false)
        return FooterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FooterViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 1
}