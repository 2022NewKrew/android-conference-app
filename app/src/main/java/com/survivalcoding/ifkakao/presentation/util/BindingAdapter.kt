package com.survivalcoding.ifkakao.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.presentation.base.UiState

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("thumbnailImage")
    fun ImageView.bindThumbnailImage(url: String) {
        Glide.with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .fitCenter()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("mailImage")
    fun ImageView.bindHighlightTitleImage(url: String) {
        Glide.with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .fitCenter()
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("itemDecoration")
    fun RecyclerView.bindItemDecoration(itemDecoration: RecyclerView.ItemDecoration) {
        addItemDecoration(itemDecoration)
    }

    @JvmStatic
    @BindingAdapter("drawableIcon")
    fun ImageView.bindIcon(url: String) {
        Glide.with(context)
            .load(url)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("sessions")
    fun RecyclerView.bindSessionItems(uiState: UiState) {
        val sessionAdapter = this.adapter
        if (sessionAdapter is SessionListAdapter && uiState is UiState.Success<*>) {
            sessionAdapter.submitList(uiState.data as List<IkSessionData>)
        }
    }

    @JvmStatic
    @BindingAdapter("adapter")
    fun RecyclerView.bindAdapter(adapter: RecyclerView.Adapter<*>) {
        this.adapter = adapter
    }
}
