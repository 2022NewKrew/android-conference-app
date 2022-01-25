package com.survivalcoding.ifkakao.domain.model

data class Content(
    val code: Int,
    val count: Int,
    val `data`: List<Data>,
    val errorMessage: String?,
    val success: Boolean
)