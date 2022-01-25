package com.example.domain.usecase

import com.example.domain.repository.ConferencesRepository

class GetConferencesDataSource(private val conferencesRepository: ConferencesRepository) {
    suspend operator fun invoke() = conferencesRepository.getConferences()
}