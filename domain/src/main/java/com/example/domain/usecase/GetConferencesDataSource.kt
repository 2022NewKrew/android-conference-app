package com.example.domain.usecase

import com.example.domain.repository.ConferencesRepository

class GetConferencesDataSource(private val conferencesRepository: ConferencesRepository) {
    operator fun invoke() = conferencesRepository.getConferences()
}