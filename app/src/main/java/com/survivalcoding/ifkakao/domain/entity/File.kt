package com.survivalcoding.ifkakao.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class File(
    val uri: String,
    val description: String
) : Parcelable
