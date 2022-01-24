package com.survivalcoding.ifkakao.domain.model

data class Session(
    val code: Int,
    val count: Int,
    val `data`: List<Data>,
    val errorMessage: Any,
    val success: Boolean
)