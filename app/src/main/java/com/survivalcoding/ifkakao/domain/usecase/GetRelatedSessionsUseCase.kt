package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.Session

class GetRelatedSessionsUseCase(private val getSessionsUseCase: GetSessionsUseCase) {
    suspend operator fun invoke(field: String, day: List<String>): List<Session> =
        getSessionsUseCase.invoke().filter {
            it.field == field && isDaySame(day, it.relationList.MAIN_EXPOSURE_DAY)
        }

    private fun isDaySame(day: List<String>, mainExposureDay: List<String>): Boolean {
        if (day.isEmpty()) {
            if (mainExposureDay.isEmpty()) return true
            return false
        }
        if (mainExposureDay.isEmpty()) return false

        return day[0] == mainExposureDay[0]
    }
}