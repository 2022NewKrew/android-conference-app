package com.survivalcoding.ifkakao.presentation.session.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ListItemKeywordBinding
import com.survivalcoding.ifkakao.domain.model.IkKeyword

class KeywordViewHolder(val parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.list_item_keyword, parent, false)
) {
    private val binding = ListItemKeywordBinding.bind(itemView)

    fun bind(
        keyword: IkKeyword,
        onClickListener: (keyword: IkKeyword) -> Unit,
    ) {
        binding.ikKeyword = keyword
        binding.executePendingBindings()

        binding.keywordName.setBackgroundColor(
            if (keyword.isSelected) Color.WHITE
            else R.drawable.list_keyword
        )

        binding.keywordName.apply {
            if (keyword.isSelected) {
                setBackgroundResource(R.drawable.list_keyword_selected)
                setTextColor(Color.rgb(16, 16, 16))
            } else {
                setBackgroundResource(R.drawable.list_keyword)
                setTextColor(Color.rgb(156, 156, 156))
            }
        }

        itemView.setOnClickListener {
            onClickListener(keyword)
        }
    }
}