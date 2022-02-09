package com.survivalcoding.ifkakao

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

class MyView : View {

    constructor(context: Context) : super(context) {

    }

    // 얘를 통하게 만들면 됨
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val animator = ObjectAnimator.ofFloat(this, "animation", 200f)
        animator.duration = 1000
        animator.start()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

    }

    var color = Color.BLUE

    var path = Path().apply {
        moveTo(100f, 100f)
        lineTo(200f, 100f)
        close()
    }

    val paint = Paint().apply {
        color = Color.RED
        strokeWidth = 10F
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }

    var startX = 0f
    var startY = 0f
    var endX = 0f
    var endY = 0f

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
//                color = Color.RED
                Log.d("MyView", "onTouchEvent: ")
                startX = event.x
                startY = event.y
                endX = event.x
                endY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                endX = event.x
                endY = event.y
            }
            MotionEvent.ACTION_UP -> {
                startX = 0f
                startY = 0f
                endX = 0f
                endY = 0f
            }
            else -> {
//                color = Color.BLUE
            }
        }
        invalidate()

        return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.d("MyView", "onSizeChanged: ")
    }

    private fun setAnimation(x: Float) {
        path = Path().apply {
            moveTo(100f, 100f)
            lineTo(x, 100f)
            close()
        }

        invalidate()
    }
    // 그리기
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(color)

        canvas.drawLine(0f, 0f, 100f, 100f, paint)

        canvas.drawPath(path, paint)

        canvas.drawRect(startX, startY, endX, endY, paint)
    }

    // 위치
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d("MyView", "onLayout: ")
    }

    // 크기
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
        Log.d("MyView", "onMeasure: ")

    }
}


class MyViewGroup : ViewGroup {
    constructor(context: Context) : super(context) {

    }

    // 얘를 통하게 만들면 됨
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        TODO("Not yet implemented")
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        if (getChildAt(0).onTouchEvent(ev)) {

            return true
        }
        return false

    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return super.onInterceptTouchEvent(ev)
    }


}