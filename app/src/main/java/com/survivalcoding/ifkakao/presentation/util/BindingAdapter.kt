package com.survivalcoding.ifkakao.presentation.util

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.presentation.base.UiState
import com.survivalcoding.ifkakao.presentation.highlight.HighlightUIState
import com.survivalcoding.ifkakao.presentation.util.BindingAdapter.bindHighlightTitleImage

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
    @BindingAdapter("uiStatePortrait")
    fun ImageView.bindHighlightTitleImage(uiState: UiState) {
        if (uiState is UiState.Success<*>) {
            val highlightUIState = uiState.data as HighlightUIState
            Glide.with(context)
                .load(highlightUIState.backgroundMobile)
                .transition(DrawableTransitionOptions.withCrossFade())
                .fitCenter()
                .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("drawableIcon")
    fun ImageView.bindIcon(uiState: UiState) {
        if (uiState is UiState.Success<*>) {
            val highlightUIState = uiState.data as HighlightUIState
            Glide.with(context)
                .load(highlightUIState.icon)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("sessions")
    fun RecyclerView.bindSessionItems(uiState: UiState) {
        val sessionAdapter = this.adapter
        if (sessionAdapter is SessionListAdapter && uiState is UiState.Success<*>) {
            sessionAdapter.submitList((uiState.data as HighlightUIState).highlightSessions)
        }
    }
}
