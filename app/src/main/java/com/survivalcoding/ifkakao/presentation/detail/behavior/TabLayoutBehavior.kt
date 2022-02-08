package com.survivalcoding.ifkakao.presentation.detail.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.survivalcoding.ifkakao.R
import kotlin.math.abs


class TabLayoutBehavior(
    private val context: Context,
    attrs: AttributeSet,
) : CoordinatorLayout.Behavior<TabLayout>(context, attrs) {

    private val getYMax =
        context.resources.getDimension(R.dimen.appbar_height) - context.resources
            .getDimension(R.dimen.toolbar_height);

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: TabLayout,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: TabLayout,
        dependency: View
    ): Boolean {
        child.y = getRatioValue(
            dpToPx(200f),
            dpToPx(140f),
            abs(dependency.y),
            getYMax
        )

        return false
    }

    private fun dpToPx(dp: Float): Float {
        val dm: DisplayMetrics = context.resources.displayMetrics
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