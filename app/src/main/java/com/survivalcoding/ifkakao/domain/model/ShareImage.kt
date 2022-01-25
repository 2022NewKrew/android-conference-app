package com.survivalcoding.ifkakao.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShareImage(
    val contentsIdx: Int,
    val description: String,
    val fileSize: Int,
    val idx: Int,
    val mainYn: String,
    val type: String,
    val url: String
): Parcelable