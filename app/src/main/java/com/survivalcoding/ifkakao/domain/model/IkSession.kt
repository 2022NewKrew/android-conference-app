package com.survivalcoding.ifkakao.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IkSession(
    val success: Boolean = true,
    val sessions: List<IkSessionData> = listOf(),
    val errorMessage: String = "",
) : Parcelable