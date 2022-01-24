package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.IkListItem
import com.survivalcoding.ifkakao.domain.repository.IkContentsRepository

class GetListItemsUseCase(
    private val repository: IkContentsRepository
) : BaseUseCase() {
    suspend operator fun invoke(): Result<List<IkListItem>> {
        return result { repository.getListItems() as List<IkListItem>? ?: listOf() }
    }
}