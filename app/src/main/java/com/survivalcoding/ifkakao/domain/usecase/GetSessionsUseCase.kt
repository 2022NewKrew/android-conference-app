package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.IkListItem
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.repository.IkContentsRepository

class GetSessionsUseCase(
    private val repository: IkContentsRepository
) : BaseUseCase() {
    suspend operator fun invoke(): Result<List<IkSessionData>> {
        return result { repository.getSessions() }
    }
}