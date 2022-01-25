package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.repository.SessionRepository

class LikeSessionUseCase(private val repository: SessionRepository) {
    suspend operator fun invoke(session: Session) = repository.likeSession(session)
}