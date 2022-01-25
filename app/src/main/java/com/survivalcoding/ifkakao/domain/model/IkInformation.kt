package com.survivalcoding.ifkakao.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IkInformation(
    val isValid: Boolean = true,
    val idx: Int = -1,
    val url: String = "",
    val description: String = "",
    val mainYn: String = "",
) : Parcelable
