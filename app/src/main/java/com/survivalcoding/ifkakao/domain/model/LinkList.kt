package com.survivalcoding.ifkakao.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinkList(
    val PC_IMAGE: List<PCImage>,
    val SHARE_IMAGE: List<SHAREIMAGE>,
    val SPEAKER_PROFILE: List<SPEAKERPROFILE>,
    val VIDEO: List<VIDEO>,
): Parcelable