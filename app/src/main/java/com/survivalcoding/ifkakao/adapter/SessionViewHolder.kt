package com.survivalcoding.ifkakao.adapter

import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.Data
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ItemSessionBinding

class SessionViewHolder(private val binding: ItemSessionBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(item: Data, onClicked: (Data) -> Unit) {
        binding.sessionTitle.text = item.title
        binding.sessionCompany.text = item.company
        binding.sessionField.text = item.field

/*
        try {
            Glide.with(binding.root).load(item.linkList?.moImage?.first()?.url)
                .into(binding.sessionThumbnail)
        } catch (e: Exception) {
            Log.d("No List", "ThumbNail ${item.idx}")
            binding.sessionThumbnail.setBackgroundResource(R.drawable.ic_baseline_not_interested_24)
        }
*/
        val video = item.linkList?.video?.first()
        if (video?.description != null) {
            binding.sessionVideoTime.text = video.description
        } else {
            binding.sessionVideoTime.visibility = View.GONE
        }
        if (item.isLike) {
            binding.likeImg.setBackgroundResource(R.drawable.love)
        }

        binding.root.setOnClickListener {
            onClicked(item)
        }
    }

    companion object {
        fun builder(parent: ViewGroup): SessionViewHolder = SessionViewHolder(
            ItemSessionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}