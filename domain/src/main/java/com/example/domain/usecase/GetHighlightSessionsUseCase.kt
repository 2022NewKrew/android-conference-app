package com.example.domain.usecase

import com.example.domain.repository.ConferencesRepository
import javax.inject.Inject

class GetHighlightSessionsUseCase @Inject constructor(private val conferencesRepository: ConferencesRepository) {
    suspend operator fun invoke() = conferencesRepository.getSpotLightedSessions()
}