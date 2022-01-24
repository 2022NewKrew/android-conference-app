package com.survivalcoding.ifkakao.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IkLinkLists(
    val isValid: Boolean = true,
    val pcImage: List<IkInformation> = listOf(),
    val video: List<IkInformation> = listOf(),
    val speakerProfile: List<IkInformation> = listOf(),
) : Parcelable