package com.survivalcoding.ifkakao.presentation.liked.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R

class LikedHeaderAdapter : RecyclerView.Adapter<LikedHeaderAdapter.LikedHeaderViewHolder>() {
    inner class LikedHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LikedHeaderAdapter.LikedHeaderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.header_liked, parent, false)
        return LikedHeaderViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: LikedHeaderAdapter.LikedHeaderViewHolder,
        position: Int
    ) {
    }

    override fun getItemCount(): Int = 1
}