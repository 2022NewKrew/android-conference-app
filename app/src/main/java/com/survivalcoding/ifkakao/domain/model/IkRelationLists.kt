package com.survivalcoding.ifkakao.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IkRelationLists(
    val isValid: Boolean = true,
    val classification: List<String> = listOf(),
    val techClassification: List<String> = listOf(),
    val mainExposureDay: List<String> = listOf(),
) : Parcelable