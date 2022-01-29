package com.survivalcoding.ifkakao.presentation.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.survivalcoding.ifkakao.domain.model.IkSessionData

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("image")
    fun ImageView.bindMainImage(url: String) {
        Glide.with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("thumbnail")
    fun ImageView.bindThumbnailImage(url: String) {
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
    @BindingAdapter("sessions")
    fun RecyclerView.bindSessions(sessions: List<IkSessionData>?) {
        sessions?.let {
            val adapter = this.adapter as ConcatAdapter
            adapter.adapters.forEach { if (it is SessionListAdapter) it.submitList(sessions) }
        }
    }

    @JvmStatic
    @BindingAdapter("currentDay")
    fun TextView.bindCurrentDay(currentDay: Int?) {
        currentDay?.let {
            text = when (currentDay) {
                1 -> "Day1"
                2 -> "Day2"
                else -> "Day3(All)"
            }
        }
    }
}
