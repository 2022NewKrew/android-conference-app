package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.IkMainItem
import com.survivalcoding.ifkakao.domain.repository.IkContentsRepository

class GetMainItemsUseCase(
    private val repository: IkContentsRepository
) : BaseUseCase() {
    suspend operator fun invoke(): Result<List<IkMainItem>> {
        return result { repository.getMainItems() as List<IkMainItem>? ?: listOf() }
    }
}