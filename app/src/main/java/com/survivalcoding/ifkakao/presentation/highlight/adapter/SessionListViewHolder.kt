package com.survivalcoding.ifkakao.presentation.highlight.adapter

import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ContentListItemBinding
import com.survivalcoding.ifkakao.domain.model.IkSessionData

class SessionListViewHolder(val parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.content_list_item, parent, false)
) {
    private val binding = ContentListItemBinding.bind(itemView)

    fun bind(
        session: IkSessionData,
        onClickListener: (session: IkSessionData) -> Unit,
    ) {
        binding.tvHighlightTitle.text = session.title
        binding.tvVideoLength.isVisible = session.isVideo
        binding.tvVideoLength.text = session.video.videoLength

        binding.listCompany.text = session.company
        binding.listField.text = session.field

        val imageUrl = session.video.thumbnailUrl
        Glide.with(parent.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .fitCenter()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .into(binding.ivListItemThumbnail)

        itemView.setOnClickListener {
            onClickListener(session)
        }
    }
}