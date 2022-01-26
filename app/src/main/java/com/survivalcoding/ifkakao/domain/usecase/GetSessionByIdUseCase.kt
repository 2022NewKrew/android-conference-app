package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.Session

class GetSessionByIdUseCase(private val getSessionsUseCase: GetSessionsUseCase) {
    suspend operator fun invoke(idx: Int): Session =
        getSessionsUseCase.invoke().first { it.idx == idx }
}