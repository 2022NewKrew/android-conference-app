package com.example.domain.usecase

import com.example.domain.entity.ContentState
import com.example.domain.entity.OrderState
import com.example.domain.repository.ConferencesRepository
import javax.inject.Inject

class GetSortedDateWithKeyWordsSessionsUseCase @Inject constructor(private val conferencesRepository: ConferencesRepository) {
    suspend operator fun invoke(
        date: Int,
        keyWords: List<String>,
        contentState: ContentState,
        orderState: OrderState
    ) =
        conferencesRepository.getSortedDateWithKeyWordsSessions(
            date,
            keyWords,
            contentState,
            orderState
        )
}