package com.example.data.repositoryImpl

import com.example.data.network.IfKakaoService
import com.example.domain.entity.Conference
import com.example.domain.repository.ConferencesRepository
import kotlinx.coroutines.flow.Flow

class ConferenceRepositoryImpl(private val service: IfKakaoService) : ConferencesRepository {
    override suspend fun getConferences(): Conference = service.getConferences()

}