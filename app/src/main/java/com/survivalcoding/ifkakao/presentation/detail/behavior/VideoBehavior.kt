package com.survivalcoding.ifkakao.presentation.detail.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.webkit.WebView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.survivalcoding.ifkakao.R
import kotlin.math.abs


class VideoBehavior(
    private val context: Context,
    attrs: AttributeSet,
) : CoordinatorLayout.Behavior<WebView>(context, attrs) {

    private val getYMax =
        context.resources.getDimension(R.dimen.appbar_height) - context.resources
            .getDimension(R.dimen.toolbar_height);

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: WebView,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: WebView,
        dependency: View
    ): Boolean {

        child.scaleX = getRatioValue(
            1f,
            0.7f,
            abs(dependency.y),
            getYMax,
        )

        child.scaleY = getRatioValue(
            1f,
            0.7f,
            abs(dependency.y),
            getYMax,
        )

        child.x = getRatioValue(
            0f,
            0.15f,
            abs(dependency.y),
            getYMax,
        )

        child.pivotY = 0f

        return true
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