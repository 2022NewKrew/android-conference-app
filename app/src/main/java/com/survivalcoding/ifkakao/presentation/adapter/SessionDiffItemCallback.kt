package com.survivalcoding.ifkakao.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.ifkakao.domain.model.Session

object SessionDiffItemCallback : DiffUtil.ItemCallback<Session>() {
    override fun areItemsTheSame(oldItem: Session, newItem: Session): Boolean {
        return oldItem.idx == newItem.idx
    }

    override fun areContentsTheSame(oldItem: Session, newItem: Session): Boolean {
        return oldItem == newItem
    }
}