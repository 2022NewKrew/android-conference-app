package com.example.domain.entity

data class SpeakerProfile(
    val idx: Int,
    val contentsIdx: Int,
    val type: String,
    val fileSize: Int,
    val url: String,
    val description: String,
    val mainYn: String
)