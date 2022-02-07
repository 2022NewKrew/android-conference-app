package com.survivalcoding.ifkakao.presentation.commons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ItemFooterBinding


class FooterAdapter(
    private val onClickUpButton: (Unit) -> Unit,
    private val onClickSite: (Unit) -> Unit
) :
    RecyclerView.Adapter<FooterAdapter.FooterViewHolder>() {
    inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemFooterBinding = ItemFooterBinding.bind(itemView)
        fun bind() {
            binding.upButton.setOnClickListener {
                onClickUpButton(Unit)
            }
            binding.prevIfKakao.setOnClickListener {
                //ToDo: 2018-2020 누를 수 있게 변경
                onClickSite(Unit)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FooterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_footer, parent, false)
        return FooterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FooterViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 1
}