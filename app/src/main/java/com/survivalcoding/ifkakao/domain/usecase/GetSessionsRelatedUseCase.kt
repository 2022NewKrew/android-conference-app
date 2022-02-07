package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import javax.inject.Inject

class GetSessionsRelatedUseCase @Inject constructor(private val repository: SessionRepository) {
    suspend operator fun invoke(id: Int, field: String) = repository.getSessionsRelated(id, field)
}