package com.survivalcoding.ifkakao.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContentsSpeaker(
    val channelLink: String,
    val company: String,
    val contentsIdx: Int,
    val idx: Int,
    val loginEmail: String,
    val nameEn: String,
    val nameKo: String,
    val occupation: String
): Parcelable