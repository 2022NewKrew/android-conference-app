package com.survivalcoding.ifkakao.data.dto

import com.survivalcoding.ifkakao.data.vo.DataVo

data class ApiDto(
    val code: Int,
    val count: Int,
    val `data`: List<DataVo>,
    val errorMessage: Any,
    val success: Boolean
)