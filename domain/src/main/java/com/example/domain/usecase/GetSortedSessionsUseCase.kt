package com.example.domain.usecase

import com.example.domain.entity.ContentState
import com.example.domain.entity.OrderState
import com.example.domain.repository.ConferencesRepository
import javax.inject.Inject

class GetSortedSessionsUseCase @Inject constructor(private val conferencesRepository: ConferencesRepository) {
    suspend operator fun invoke(date: Int, contentState: ContentState, orderState: OrderState) =
        conferencesRepository.getSortedDateSessions(date, contentState, orderState)
}