package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.Session

class GetRelatedSessionsUseCase(private val getSessionsUseCase: GetSessionsUseCase) {
    suspend operator fun invoke(day: String, field: String): List<Session> =
        getSessionsUseCase.invoke().filter {
            it.relationList.MAIN_EXPOSURE_DAY.first() == day &&
                    it.field == field
        }
}