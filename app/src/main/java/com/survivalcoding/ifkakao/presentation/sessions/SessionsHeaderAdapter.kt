package com.survivalcoding.ifkakao.presentation.sessions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R

class SessionsHeaderAdapter(
    private val onClickTab: (Int) -> Unit,
    private val onClickFilter: () -> Unit,
) : RecyclerView.Adapter<SessionsHeaderViewHolder>() {
    private var selected: Int = 0
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SessionsHeaderViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_sessions_header, parent, false)
        return SessionsHeaderViewHolder(view, onClickTab, onClickFilter)
    }

    override fun onBindViewHolder(holder: SessionsHeaderViewHolder, position: Int) {
        holder.bind(selected)
    }

    override fun getItemCount(): Int = 1
    fun setTabSelection(idx: Int) {
        selected = idx
    }
}
