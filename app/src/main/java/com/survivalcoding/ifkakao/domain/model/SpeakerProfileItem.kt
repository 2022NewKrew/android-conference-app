package com.survivalcoding.ifkakao.domain.model

data class SpeakerProfileItem(
    val idx: Int,
    val profileImageUrl: String,
    val nameKo: String,
    val nameEn: String,
    val company: String?,
    val occupation: String?,
    val channelLink: String?
)
