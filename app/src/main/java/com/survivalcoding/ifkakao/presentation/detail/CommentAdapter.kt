package com.survivalcoding.ifkakao.presentation.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R

class CommentAdapter :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_detail_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int = 1
    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {}
}
