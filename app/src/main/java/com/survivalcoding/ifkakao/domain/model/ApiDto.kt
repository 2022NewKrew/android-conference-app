package com.survivalcoding.ifkakao.domain.model

data class ApiDto(
    val code: Int,
    val count: Int,
    val `data`: List<DataVo>,
    val errorMessage: Any,
    val success: Boolean
)