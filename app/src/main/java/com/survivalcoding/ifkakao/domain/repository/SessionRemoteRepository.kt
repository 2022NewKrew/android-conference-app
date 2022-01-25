package com.survivalcoding.ifkakao.domain.repository

import com.survivalcoding.ifkakao.domain.model.IfKakaoData

interface SessionRemoteRepository {
    suspend fun getIfKakaoData(): IfKakaoData
}