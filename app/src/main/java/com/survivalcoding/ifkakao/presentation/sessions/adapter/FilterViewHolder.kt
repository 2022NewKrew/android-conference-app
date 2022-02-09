package com.survivalcoding.ifkakao.presentation.sessions.adapter

import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ItemFilterBinding

class FilterViewHolder(
    private val binding: ItemFilterBinding,
    private val itemClickListener: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(keyword: String, initSelected: Boolean) {
        binding.filterTvKeyword.text = keyword

        var textColor: Int

        if (initSelected) {
            binding.filterTvKeyword.backgroundTintList = ResourcesCompat.getColorStateList(
                binding.root.resources,
                R.color.white,
                null
            )

            binding.filterTvKeyword.setTextColor(
                binding.root.resources.getColor(
                    R.color.black,
                    null
                )
            )
        }

        binding.root.setOnClickListener {
            itemClickListener(keyword)

            if (binding.filterTvKeyword.currentTextColor == binding.root.resources.getColor(
                    R.color.light_gray,
                    null
                )
            ) {
                textColor = R.color.black
                binding.filterTvKeyword.backgroundTintList = ResourcesCompat.getColorStateList(
                    binding.root.resources,
                    R.color.white,
                    null
                )
            } else {
                textColor = R.color.light_gray
                binding.filterTvKeyword.backgroundTintList = null
            }

            binding.filterTvKeyword.setTextColor(
                binding.root.resources.getColor(
                    textColor,
                    null
                )
            )
        }
    }
}