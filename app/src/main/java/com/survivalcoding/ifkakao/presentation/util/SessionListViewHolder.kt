package com.survivalcoding.ifkakao.presentation.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ContentListItemBinding
import com.survivalcoding.ifkakao.domain.model.IkSessionData

class SessionListViewHolder(val parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.content_list_item, parent, false)
) {
    private val binding = ContentListItemBinding.bind(itemView)

    fun bind(
        session: IkSessionData,
        onClickListener: (session: IkSessionData) -> Unit,
    ) {
        binding.sessionData = session
        binding.executePendingBindings()

        itemView.setOnClickListener {
            onClickListener(session)
        }
    }
}