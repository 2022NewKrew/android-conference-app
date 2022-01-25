package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.repository.IkContentsRepository

class GetSessionsUseCase(
    private val repository: IkContentsRepository,
) {
    suspend operator fun invoke(): List<IkSessionData> {
        return repository.getSessions()
    }
}