package com.example.domain.entity

import com.google.gson.annotations.SerializedName


data class RelationList(

    @SerializedName("CLASSIFICATION") val classification: List<String>?,
    @SerializedName("TECH_CLASSIFICATION") val techClassification: List<String>?,
    @SerializedName("MAIN_EXPOSURE_DAY") val mainExposureDay: List<String>?
)