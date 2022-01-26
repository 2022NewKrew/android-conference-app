package com.survivalcoding.ifkakao.domain.model

data class ContentsSpeakerVo(
    val channelLink: String,
    val company: String,
    val contentsIdx: Int,
    val idx: Int,
    val loginEmail: String,
    val nameEn: String,
    val nameKo: String,
    val occupation: String
)