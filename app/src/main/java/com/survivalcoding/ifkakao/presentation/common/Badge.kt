package com.survivalcoding.ifkakao.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.BadgeBinding

class Badge @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: BadgeBinding by lazy {
        BadgeBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    var isHighlight: Boolean = false
        set(value) {
            field = value
            if (value) {
                binding.textView.setTextColor(ResourcesCompat.getColor(resources, R.color.yellow, null))
                binding.root.background = ResourcesCompat.getDrawable(resources, R.drawable.badge_border_yellow, null)
            } else {
                binding.textView.setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
                binding.root.background = ResourcesCompat.getDrawable(resources, R.drawable.badge_background_white, null)
            }
        }

    var text: String = ""
        set(value) {
            field = value
            binding.textView.text = value
        }
}