package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.repository.SessionRemoteRepository

class GetSessionsUseCase(private val repository: SessionRemoteRepository) {
    suspend operator fun invoke(): List<Session> = repository.getIfKakaoData().data
}