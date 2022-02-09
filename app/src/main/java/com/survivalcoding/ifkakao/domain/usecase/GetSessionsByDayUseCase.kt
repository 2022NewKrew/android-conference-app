package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.Session

class GetSessionsByDayUseCase(private val getSessionsUseCase: GetSessionsUseCase) {
    suspend operator fun invoke(day: Int): List<Session> =
        getSessionsUseCase.invoke().filter {
            if (day == 3) true
            else it.reservationDate == (day + START_DATE).toString()
        }

    companion object {
        const val START_DATE = 20211115
    }
}