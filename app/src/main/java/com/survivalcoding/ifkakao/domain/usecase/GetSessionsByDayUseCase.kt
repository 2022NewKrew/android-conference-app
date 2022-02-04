package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.Session

class GetSessionsByDayUseCase(private val getSessionsUseCase: GetSessionsUseCase) {
    suspend operator fun invoke(day: Int):  List<Session> =
        getSessionsUseCase.invoke().filter{
            it.relationList.MAIN_EXPOSURE_DAY[0][0] == day.toChar()
        }
}