package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.repository.SessionRepository

class GetLikeSessionUseCase(private val repository: SessionRepository) {
    suspend operator fun invoke() = repository.getSessionLike()
}