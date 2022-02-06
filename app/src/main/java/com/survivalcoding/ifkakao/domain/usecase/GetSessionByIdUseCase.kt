package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import javax.inject.Inject

class GetSessionByIdUseCase @Inject constructor(private val repository: SessionRepository) {
    suspend operator fun invoke(id: Int) = repository.getSessionById(id)
}