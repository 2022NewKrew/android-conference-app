package com.survivalcoding.ifkakao.presentation.keyword.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.HeaderKeywordBinding
import com.survivalcoding.ifkakao.domain.model.IkTagInfo

class KeywordHeaderAdapter(
    private val tag: IkTagInfo,
) : RecyclerView.Adapter<KeywordHeaderAdapter.KeywordHeaderViewHolder>() {
    inner class KeywordHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = HeaderKeywordBinding.bind(itemView)
        fun bind(keyword: IkTagInfo) {
            binding.title = keyword
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordHeaderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.header_keyword, parent, false)
        return KeywordHeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: KeywordHeaderViewHolder, position: Int) {
        holder.bind(tag)
    }

    override fun getItemCount(): Int = 1
}