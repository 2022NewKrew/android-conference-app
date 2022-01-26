package com.example.data.network

import com.example.domain.entity.Conference
import com.google.gson.Gson
import javax.inject.Inject

class ConferenceDataStore @Inject constructor(private val service: IfKakaoService) {
    private val conferences: Conference? = Gson().fromJson(JsonData.json, Conference::class.java)
    suspend fun getConferences() = conferences ?: service.getConferences()
}