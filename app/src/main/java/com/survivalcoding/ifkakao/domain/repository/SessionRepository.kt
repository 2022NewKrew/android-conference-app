package com.survivalcoding.ifkakao.domain.repository

import com.survivalcoding.ifkakao.domain.model.IfKakaoContent

interface SessionRepository {
    suspend fun getContent(): IfKakaoContent
}
