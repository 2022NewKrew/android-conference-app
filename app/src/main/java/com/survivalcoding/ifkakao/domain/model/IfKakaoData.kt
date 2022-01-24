package com.survivalcoding.ifkakao.domain.model

data class IfKakaoData(
    val count: Int = 0,
    val `data`: List<Session> = emptyList(),
)