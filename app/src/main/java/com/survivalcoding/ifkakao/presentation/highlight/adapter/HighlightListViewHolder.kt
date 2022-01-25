package com.survivalcoding.ifkakao.presentation.highlight.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ContentListItemBinding
import com.survivalcoding.ifkakao.domain.model.IkSessionData

class HighlightListViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.content_list_item, parent, false)
) {
    private val binding = ContentListItemBinding.bind(itemView)

    fun bind(
        session: IkSessionData,
        clickListener: (session: IkSessionData) -> Unit,
    ) {
        binding.tvHighlightTitle.text = session.title
        if (session.linkLists.video.isEmpty()) {
            binding.tvVideoLength.isVisible = false
        } else {
            binding.tvVideoLength.isVisible = true
            binding.tvVideoLength.text = session.linkLists.video.first().description
        }

        binding.listCompany.text = session.company
        binding.listField.text = session.field

        val imageUrl = session.linkLists.pcImage.firstOrNull()?.url ?: ""
        binding.ivListItemThumbnail.load(imageUrl) {
            transformations(RoundedCornersTransformation(radius = 5f))
        }

        itemView.setOnClickListener {
            clickListener(session)
        }
    }
}