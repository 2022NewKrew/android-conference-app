package com.survivalcoding.ifkakao.presentation.util

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.domain.model.IkSessionData

class SessionListAdapter(
    private val threshold: Int = -1,
    private val load: () -> Unit = {},
    private val onClickListener: (session: IkSessionData) -> Unit,
) : ListAdapter<IkSessionData, SessionListViewHolder>(object :
    DiffUtil.ItemCallback<IkSessionData>() {
    override fun areItemsTheSame(oldItem: IkSessionData, newItem: IkSessionData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: IkSessionData, newItem: IkSessionData): Boolean {
        return oldItem == newItem
    }
}) {
    private val onScrollListener = object : RecyclerView.OnScrollListener() {

        private var isScrollingUp = false

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (isScrollingUp) {
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                if (itemCount - lastVisibleItemPosition <= threshold) {
                    load()
                }
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            isScrollingUp = dy > 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionListViewHolder {
        return SessionListViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SessionListViewHolder, position: Int) {
        holder.bind(
            session = getItem(position),
            onClickListener = onClickListener,
        )
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (threshold != -1) recyclerView.addOnScrollListener(onScrollListener)
    }
}