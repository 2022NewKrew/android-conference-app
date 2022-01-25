package com.survivalcoding.ifkakao.data.dto

data class IkContentDTO(
    val code: Int? = null,
    val count: Int? = null,
    val `data`: List<IkSessionDataDTO>? = null,
    val errorMessage: Any? = null,
    val success: Boolean? = null,
)