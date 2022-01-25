package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.repository.SessionRepository

class GetSessionsUseCase(private val repository: SessionRepository) {
    suspend operator fun invoke(): List<Session> = repository.getIfKakaoData().data
}