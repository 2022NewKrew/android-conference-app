package com.survivalcoding.ifkakao.presentation.detail

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ItemDetailExplanationBinding
import com.survivalcoding.ifkakao.domain.model.Session

class ExplanationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemDetailExplanationBinding.bind(itemView)
    private val ctx: Context = itemView.context
    fun bind(session: Session) {
        val fields: List<String> =
            listOf(session.company!!, session.field) + session.relationList.CLASSIFICATION!!
        binding.SessionInfoLayout.removeAllViews()
        for (str in fields) {
            val text = TextView(ctx)
            text.text = str
            text.setTextColor(ContextCompat.getColor(ctx, R.color.light_gray))
            text.background = ContextCompat.getDrawable(ctx, R.drawable.bg_gray_box)
            text.setPadding(20 / (itemView.resources.displayMetrics.density).toInt())

            binding.SessionInfoLayout.addView(text)
        }

        val title = binding.titleText
        title.text = session.title

        val content = binding.contentText
        content.text = session.content
    }
}