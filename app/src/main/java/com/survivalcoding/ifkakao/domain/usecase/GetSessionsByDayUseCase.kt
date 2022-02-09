package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.DayType
import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import javax.inject.Inject

class GetSessionsByDayUseCase @Inject constructor(private val repository: SessionRepository) {
    suspend operator fun invoke(day: DayType) = when (day) {
        DayType.Day1 -> repository.getSessionsByDay("1Day")
        DayType.Day2 -> repository.getSessionsByDay("2Day")
        else -> repository.getSessionAll()
    }
}