package com.survivalcoding.ifkakao.presentation.session.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.ifkakao.domain.model.IkKeyword

class KeywordAdapter(
    private val onClickListener: (keyword: IkKeyword) -> Unit,
) : ListAdapter<IkKeyword, KeywordViewHolder>(object :
    DiffUtil.ItemCallback<IkKeyword>() {
    override fun areItemsTheSame(oldItem: IkKeyword, newItem: IkKeyword): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: IkKeyword, newItem: IkKeyword): Boolean {
        return oldItem == newItem
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder {
        return KeywordViewHolder(parent)
    }

    override fun onBindViewHolder(holder: KeywordViewHolder, position: Int) {
        holder.bind(
            keyword = getItem(position),
            onClickListener = onClickListener,
        )
    }
}