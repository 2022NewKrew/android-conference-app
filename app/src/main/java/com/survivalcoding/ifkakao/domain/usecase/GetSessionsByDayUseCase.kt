package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import javax.inject.Inject

class GetSessionsByDayUseCase @Inject constructor(private val repository: SessionRepository) {
    suspend operator fun invoke(day: String) = when (day) {
        "1Day" -> repository.getSessionsByDay(day)
        "2Day" -> repository.getSessionsByDay(day)
        else -> repository.getSessionAll()
    }
}