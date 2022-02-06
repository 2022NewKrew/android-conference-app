package com.survivalcoding.ifkakao.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RelationList(
    val CLASSIFICATION: List<String>,
    val MAIN_EXPOSURE_DAY: List<String>,
    val TECH_CLASSIFICATION: List<String>
): Parcelable