package com.survivalcoding.ifkakao.presentation.detail.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import com.google.android.material.appbar.AppBarLayout
import com.survivalcoding.ifkakao.R
import kotlin.math.abs


class BehaviorVideo(
    private val context: Context,
    private val attrs: AttributeSet,
) : CoordinatorLayout.Behavior<ConstraintLayout>(context, attrs) {

    private val getYMax =
        context.resources.getDimension(R.dimen.appbar_height) - context.resources.getDimension(R.dimen.toolbar_height)

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: ConstraintLayout,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: ConstraintLayout,
        dependency: View
    ): Boolean {
        for (c in child.children) {
            if (c is ImageView) {
                c.y = getRatioValue(dpToPx(230F), dpToPx(150F), abs(dependency.y), getYMax)
            }
        }

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