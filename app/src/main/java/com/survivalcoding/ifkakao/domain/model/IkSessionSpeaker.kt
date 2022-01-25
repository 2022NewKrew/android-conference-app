package com.survivalcoding.ifkakao.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IkSessionSpeaker(
    val isValid: Boolean = true,
    val nameEn: String = "",
    val nameKo: String = "",
    val occupation: String = "",
    val company: String = "",
) : Parcelable