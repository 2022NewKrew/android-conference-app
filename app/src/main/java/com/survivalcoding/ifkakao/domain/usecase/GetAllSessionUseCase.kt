package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import javax.inject.Inject

class GetAllSessionUseCase @Inject constructor(private val repository: SessionRepository) {
    suspend operator fun invoke() = repository.getSessionAll()
}