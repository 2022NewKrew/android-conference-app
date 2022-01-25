package com.survivalcoding.ifkakao.domain.model

data class SpeakerProfileVo(
    val contentsIdx: Int,
    val description: String,
    val fileSize: Int,
    val idx: Int,
    val mainYn: String,
    val type: String,
    val url: String
)