package com.example.domain.entity

import com.google.gson.annotations.SerializedName



data class Video(
    val idx: Int,
    val contentsIdx: Int,
    val type: String,
    val fileSize: Int,
    val url: String,
    val description: String,
    val mainYn: String
)