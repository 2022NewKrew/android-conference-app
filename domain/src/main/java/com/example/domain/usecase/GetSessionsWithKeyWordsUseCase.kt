package com.example.domain.usecase

import com.example.domain.repository.ConferencesRepository
import javax.inject.Inject

class GetSessionsWithKeyWordsUseCase @Inject constructor(private val conferencesRepository: ConferencesRepository) {
    suspend operator fun invoke(vararg keywords: String) =
        conferencesRepository.getSessionsWithKeyWords(*keywords)
}