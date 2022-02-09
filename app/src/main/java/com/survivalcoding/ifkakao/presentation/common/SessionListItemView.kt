package com.survivalcoding.ifkakao.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.survivalcoding.ifkakao.databinding.SessionListItemBinding
import com.survivalcoding.ifkakao.domain.entity.Session

class SessionListItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: SessionListItemBinding by lazy {
        SessionListItemBinding.inflate(LayoutInflater.from(context), this, true)
    }

    var session: Session? = null
    set(value) {
        field = value
        if (value != null) {
            binding.companyBadge.isHighlight = true
            binding.companyBadge.text = value.company

            binding.categoryBadge.isHighlight = false

            binding.categoryBadge.text = value.categories.field.first().text

            if (value.videos.isNotEmpty()) {
                binding.thumbnail.description = value.videos.first().length
            }
            binding.thumbnail.thumbnailUrl = value.thumbnailUrl

            binding.titleTextView.text = value.title
        }
    }
}