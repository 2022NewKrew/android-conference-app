package com.survivalcoding.ifkakao.domain.model

data class IfKakaoContent(
    val `data`: List<IkSessionData>,
    val success: Boolean,
)