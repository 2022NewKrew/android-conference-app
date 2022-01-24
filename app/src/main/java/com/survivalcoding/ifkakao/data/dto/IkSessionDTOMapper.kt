package com.survivalcoding.ifkakao.data.dto

import com.survivalcoding.ifkakao.domain.model.IkSession

internal fun IkSessionDTO.toEntity(): IkSession {
    return IkSession(
        success = success ?: false,
        sessions = data?.map { it.toEntity() } ?: listOf(),
        errorMessage = errorMessage ?: ""
    )
}