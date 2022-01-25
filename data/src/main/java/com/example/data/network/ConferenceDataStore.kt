package com.example.data.network

import com.example.domain.entity.Conference

class ConferenceDataStore(private val service: IfKakaoService) {
    private val conferences: Conference? = null

    suspend fun getConferences() = conferences ?: service.getConferences()
}