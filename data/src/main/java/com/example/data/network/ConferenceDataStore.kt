package com.example.data.network

import com.example.domain.entity.Conference
import javax.inject.Inject

class ConferenceDataStore @Inject constructor(private val service: IfKakaoService) {
    private val conferences: Conference? = null

    suspend fun getConferences() = conferences ?: service.getConferences()
}