package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import javax.inject.Inject

class GetSessionsByFieldUseCase @Inject constructor(private val repository: SessionRepository) {
    suspend operator fun invoke(field: String) = repository.getSessionsByField(field)
}