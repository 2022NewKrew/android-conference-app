package com.survivalcoding.ifkakao.presentation.util

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R

class SessionItemDecoration : RecyclerView.ItemDecoration() {
    private val paint = Paint().apply { color = Color.rgb(57, 57, 57) }

    private val height = 3F
    private val padding = 70F

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val left = padding
        val right = parent.width - padding
        val childCount = parent.childCount

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            if (child.id == R.id.session_item_layout) {
                val params = child.layoutParams as RecyclerView.LayoutParams

                val top = (child.bottom + params.bottomMargin).toFloat()
                val bottom = top + height

                c.drawRect(left, top, right, bottom, paint)
            }
        }
    }
}