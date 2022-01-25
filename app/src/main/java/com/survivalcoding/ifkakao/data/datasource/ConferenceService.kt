package com.survivalcoding.ifkakao.data.datasource

import com.survivalcoding.ifkakao.domain.model.ApiDto
import retrofit2.Response
import retrofit2.http.GET

interface ConferenceService {
    @GET(ConferenceURL.CONFERENCE_URL)
    suspend fun getData(): Response<ApiDto>
}