package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.IkSession
import com.survivalcoding.ifkakao.domain.repository.IkContentsRepository

class GetContentUseCase(
    private val repository: IkContentsRepository
) : BaseUseCase() {
    suspend operator fun invoke(): Result<IkSession> {
        return result { repository.getContent() }
    }
}