package com.survivalcoding.ifkakao.domain.model

data class RelationList(
    val classification: List<String>,
    val mainExposureDay: List<String>,
    val techClassification: List<String>
)