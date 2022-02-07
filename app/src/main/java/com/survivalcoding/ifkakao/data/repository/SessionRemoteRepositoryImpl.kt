package com.survivalcoding.ifkakao.data.repository

import com.survivalcoding.ifkakao.data.datasource.IfKakaoService
import com.survivalcoding.ifkakao.data.dto.toEntity
import com.survivalcoding.ifkakao.domain.model.IfKakaoContent
import com.survivalcoding.ifkakao.domain.repository.SessionRepository

class SessionRemoteRepositoryImpl(
    private val ifKakaoService: IfKakaoService,
) : SessionRepository {
    override suspend fun getContent(): IfKakaoContent {
        return ifKakaoService.getContent().toEntity()
    }
}