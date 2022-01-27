package com.survivalcoding.ifkakao.data.dto

import com.survivalcoding.ifkakao.domain.model.*

internal fun IkContentDTO.toEntity(): IfKakaoContent {
    return IfKakaoContent(
        data = data.map { it.toEntity() },
        success = success,
    )
}