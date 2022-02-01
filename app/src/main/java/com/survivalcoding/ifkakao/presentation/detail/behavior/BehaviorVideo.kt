package com.survivalcoding.ifkakao.presentation.detail.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.material.appbar.AppBarLayout
import com.survivalcoding.ifkakao.R
import kotlin.math.abs


class BehaviorVideo(
    private val context: Context,
    private val attrs: AttributeSet,
) : CoordinatorLayout.Behavior<PlayerView>(context, attrs) {

    private val marginLeft = dpToPx(0F)
    private val marginTop = dpToPx(0F)
    private val marginTopAfter = dpToPx(0F)

    private val getYMax =
        context.resources.getDimension(R.dimen.appbar_height) - context.resources.getDimension(R.dimen.toolbar_height)

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: PlayerView,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: PlayerView,
        dependency: View
    ): Boolean {
        child.scaleX = getRatioValue(1f, 0.7f, abs(dependency.y), getYMax)
        child.scaleY = getRatioValue(1f, 0.7f, abs(dependency.y), getYMax)
        child.x = getRatioValue(
            marginLeft,
            (dependency.width - child.width).toFloat() / 2,
            abs(dependency.y),
            getYMax,
        )
        child.y = getRatioValue(
            marginTop,
            marginTopAfter,
            abs(dependency.y),
            getYMax,
        )

        return false
    }

    private fun dpToPx(dp: Float): Float {
        val dm = context.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, dm).toInt().toFloat()
    }

    private fun getRatioValue(
        firstValue: Float,
        lastValue: Float,
        getY: Float,
        getYMax: Float
    ): Float {
        val temp = -(firstValue - lastValue) * getY / getYMax
        return firstValue + temp
    }
}