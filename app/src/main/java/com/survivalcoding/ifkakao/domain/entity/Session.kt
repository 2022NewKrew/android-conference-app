package com.survivalcoding.ifkakao.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Session(
    val idx: Int,
    val title: String,
    val content: String,
    val contentTag: String,
    val company: String,
    val thumbnailUrl: String,
    val files: List<File>,
    val videos: List<Video>,
    val categories: Categories,
    val contentsSpeakers: List<Speaker>,
    val isHighlight: Boolean,
    val isFavorite: Boolean,
    val exposureDay: Int,
) : Parcelable