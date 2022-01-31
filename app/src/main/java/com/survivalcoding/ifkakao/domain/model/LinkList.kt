package com.survivalcoding.ifkakao.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinkList(
    val FILE: List<File>?,
    val PC_IMAGE: List<PCImage>?,
    val SHARE_IMAGE: List<ShareImage>?,
    val SPEAKER_PROFILE: List<SpeakerProfile>?,
    val VIDEO: List<Video>?,
) : Parcelable