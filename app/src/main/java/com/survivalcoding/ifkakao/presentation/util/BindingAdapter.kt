package com.survivalcoding.ifkakao.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.model.IkSessionSpeaker
import com.survivalcoding.ifkakao.domain.model.IkTagInfo
import com.survivalcoding.ifkakao.presentation.base.UiState
import com.survivalcoding.ifkakao.presentation.detail.adapter.SpeakerListAdapter
import com.survivalcoding.ifkakao.presentation.detail.adapter.TagListAdapter
import com.survivalcoding.ifkakao.presentation.util.BindingAdapter.bindTagItems

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
    @BindingAdapter("adapter")
    fun RecyclerView.bindAdapter(adapter: RecyclerView.Adapter<*>) {
        this.adapter = adapter
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
    @BindingAdapter(value = ["sessions", "adapter", "relatedSessionsCount"], requireAll = true)
    fun RecyclerView.bindSessionItems(
        sessions: List<IkSessionData>,
        adapter: RecyclerView.Adapter<*>,
        relatedSessionsCount: Int,
    ) {
        this.adapter = adapter
        if (adapter is SessionListAdapter) {
            adapter.submitList(sessions.take(relatedSessionsCount))
        }
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter(value = ["sessions", "adapter"], requireAll = true)
    fun RecyclerView.bindSessionItems(
        sessions: List<IkSessionData>,
        adapter: RecyclerView.Adapter<*>,
    ) {
        this.adapter = adapter
        if (adapter is SessionListAdapter) {
            adapter.submitList(sessions)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["tags", "adapter"], requireAll = true)
    fun RecyclerView.bindTagItems(tags: List<IkTagInfo>, adapter: RecyclerView.Adapter<*>) {
        this.adapter = adapter
        if (adapter is TagListAdapter) {
            adapter.submitList(tags)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["speakers", "adapter"], requireAll = true)
    fun RecyclerView.bindSpeakerItems(
        speakers: List<IkSessionSpeaker>,
        adapter: RecyclerView.Adapter<*>
    ) {
        this.adapter = adapter
        if (adapter is SpeakerListAdapter) {
            adapter.submitList(speakers)
        }
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("relatedSessionsCount")
    fun RecyclerView.bindSessionCountItems(count: Int) {
        this.adapter?.let {
            val adapter = this.adapter as SessionListAdapter
            adapter.submitListCount(count)
        }
    }
}
