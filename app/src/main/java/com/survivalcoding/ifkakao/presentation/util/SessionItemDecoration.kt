package com.survivalcoding.ifkakao.presentation.util

import android.graphics.Canvas
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView

class SessionItemDecoration(
    private val height: Float,
    private val padding: Float,
    color: Int,
) : RecyclerView.ItemDecoration() {
    private val paint = Paint()

    init {
        paint.color = color
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val left = padding
        val right = parent.width - padding
        val childCount = parent.childCount

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = (child.bottom + params.bottomMargin).toFloat()
            val bottom = top + height

            val adapterPosition = parent.getChildAdapterPosition(child)
            val viewType = parent.adapter?.getItemViewType(adapterPosition)
            if (viewType == 2) {
                c.drawRect(left, top, right, bottom, paint)
            }
        }
    }
}