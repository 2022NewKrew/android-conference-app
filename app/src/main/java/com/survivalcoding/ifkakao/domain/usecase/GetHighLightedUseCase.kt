package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.Session

class GetHighLightedUseCase(private val getSessionsUseCase: GetSessionsUseCase) {
    suspend operator fun invoke(): List<Session> =
        getSessionsUseCase.invoke().filter {
            it.spotlightYn == "Y"
        }
}