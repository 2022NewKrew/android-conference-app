package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import javax.inject.Inject

class GetLikeSessionUseCase @Inject constructor(private val repository: SessionRepository) {
    suspend operator fun invoke() = repository.getSessionLike()
}