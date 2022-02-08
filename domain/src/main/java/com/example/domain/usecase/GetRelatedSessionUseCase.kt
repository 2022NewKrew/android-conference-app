package com.example.domain.usecase

import com.example.domain.repository.ConferencesRepository
import javax.inject.Inject

class GetRelatedSessionUseCase @Inject constructor(private val conferencesRepository: ConferencesRepository) {
    suspend operator fun invoke(keyWords: List<String>) =
        conferencesRepository.getSessionsWithKeyWords(keyWords)
}