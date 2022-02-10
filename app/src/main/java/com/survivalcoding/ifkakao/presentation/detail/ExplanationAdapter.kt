package com.survivalcoding.ifkakao.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.domain.model.Session

class ExplanationAdapter(
    private val onClickFavoriteButton: (Boolean) -> Unit,
    private val onClickSessionButton: () -> Unit
) :
    RecyclerView.Adapter<ExplanationViewHolder>() {
    private var session: Session = Session()
    private var isFavorite: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExplanationViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_detail_explanation, parent, false)
        return ExplanationViewHolder(view, onClickFavoriteButton, onClickSessionButton)
    }

    override fun onBindViewHolder(holder: ExplanationViewHolder, position: Int) {
        if (session.idx != 0) holder.bind(session, isFavorite)
    }

    override fun getItemCount(): Int = 1

    fun updateSession(session: Session) {
        this.session = session
    }

    fun updateLiking(isFavorite: Boolean) {
        this.isFavorite = isFavorite
    }

    fun resetItem() {
        notifyItemChanged(0)
    }
}