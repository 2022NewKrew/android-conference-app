package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.repository.IkContentsRepository

class GetSessionsByTagUseCase(
    private val allSessions: List<IkSessionData>,
) {
    operator fun invoke(predicate: (IkSessionData) -> Boolean): List<IkSessionData> {
        return allSessions.filter(predicate)
    }
}