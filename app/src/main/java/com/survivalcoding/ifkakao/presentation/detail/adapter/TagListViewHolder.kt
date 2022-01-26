package com.survivalcoding.ifkakao.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.TagListItemBinding
import com.survivalcoding.ifkakao.domain.model.IkTagInfo

class TagListViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.tag_list_item, parent, false)
) {
    private val binding = TagListItemBinding.bind(itemView)

    fun bind(tag: IkTagInfo, onClickListener: (tag: IkTagInfo) -> Unit) {
        binding.tagName.text = tag.text
    }
}