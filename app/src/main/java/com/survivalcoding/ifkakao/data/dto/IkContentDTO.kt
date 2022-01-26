package com.survivalcoding.ifkakao.data.dto

data class IkContentDTO(
    val code: Int,
    val count: Int,
    val `data`: List<IkSessionDataDTO>,
    val errorMessage: Any,
    val success: Boolean,
)