package com.survivalcoding.ifkakao.data.dto

data class IkSessionDTO(
    val code: Int? = null,
    val count: Int? = null,
    val `data`: List<IkSessionDataDTO>? = null,
    val errorMessage: String? = null,
    val success: Boolean? = null,
)