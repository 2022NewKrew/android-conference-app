package com.survivalcoding.ifkakao.domain.repository

import com.survivalcoding.ifkakao.domain.model.IfKakaoData

interface SessionRepository {
    suspend fun getIfKakaoData(): IfKakaoData
    fun getMySessions()
}