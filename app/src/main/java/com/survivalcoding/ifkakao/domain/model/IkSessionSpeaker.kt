package com.survivalcoding.ifkakao.domain.model

data class IkSessionSpeaker(
    val id: Int,
    val company: String?,
    val name: String,
    val occupation: String?,
    val imageUrl: String,
)