package com.survivalcoding.ifkakao.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.domain.model.Session

class ExplanationAdapter(private val onClickFavoriteButton: (Boolean) -> Unit) :
    RecyclerView.Adapter<ExplanationViewHolder>() {
    private var session: Session = Session()
    private var isFavorite: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExplanationViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_detail_explanation, parent, false)
        return ExplanationViewHolder(view, isFavorite, onClickFavoriteButton)
    }

    override fun onBindViewHolder(holder: ExplanationViewHolder, position: Int) {
        if (session.idx != 0) holder.bind(session)
    }

    override fun getItemCount(): Int = 1

    fun updateSession(session: Session) {
        this.session = session
        notifyItemChanged(0)
    }

    fun updateLiking(isFavorite: Boolean) {
        //ToDo: 깜빡거리는 현상 해결 위해 하트 위치 옮기기
        this.isFavorite = isFavorite
        notifyItemChanged(0)
    }
}