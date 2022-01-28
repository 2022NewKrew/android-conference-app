package com.survivalcoding.ifkakao.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.domain.model.Session

class ExplanationAdapter :
    RecyclerView.Adapter<ExplanationViewHolder>() {
    private var session: Session = Session()

    fun updateSession(session: Session) {
        this.session = session
        notifyItemChanged(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExplanationViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_detail_explanation, parent, false)
        return ExplanationViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExplanationViewHolder, position: Int) {
        if (session.idx != 0) holder.bind(session)
    }

    override fun getItemCount(): Int = 1
}