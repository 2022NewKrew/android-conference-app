package com.survivalcoding.ifkakao.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinkList(
    val FILE: List<File>,
    val PC_IMAGE: List<File>,
    val SPEAKER_PROFILE: List<File>,
    val VIDEO: List<File>
) : Parcelable