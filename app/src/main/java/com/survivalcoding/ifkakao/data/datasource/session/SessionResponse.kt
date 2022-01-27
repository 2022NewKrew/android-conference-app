package com.survivalcoding.ifkakao.data.datasource.session

import com.survivalcoding.ifkakao.domain.model.Session

data class SessionResponse(
    val success: Boolean,
    val code: Int,
    val data: List<Session>,
    val count: Int
)
