package com.survivalcoding.ifkakao.domain.repository

import com.survivalcoding.ifkakao.domain.model.IfKakaoContent

interface IfKakaoRepository {
    suspend fun getContent(): IfKakaoContent
}
