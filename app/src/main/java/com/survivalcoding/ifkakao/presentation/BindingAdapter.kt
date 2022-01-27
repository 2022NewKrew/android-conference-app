package com.survivalcoding.ifkakao.presentation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.presentation.util.SessionListAdapter

@BindingAdapter("listThumbnailImageResourceUrl")
fun setListThumbnailImageResource(v: ImageView, url: String) {
    Glide.with(v.context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .fitCenter()
        .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
        .into(v)
}

@BindingAdapter("backgroundImageResourceUrl")
fun setBackgroundImageResource(v: ImageView, url: String) {
    Glide.with(v.context)
        .load(url)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(v)
}

@BindingAdapter("sessionsList")
fun submitSessionsList(v: RecyclerView, list: List<IkSessionData>) {
    val adapter = v.adapter as SessionListAdapter
    adapter.submitList(list)
}