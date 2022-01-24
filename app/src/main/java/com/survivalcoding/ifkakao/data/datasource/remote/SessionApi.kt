package com.survivalcoding.ifkakao.data.datasource.remote

import com.survivalcoding.ifkakao.domain.model.Session
import retrofit2.http.GET

interface SessionApi {
    @GET("junsuk5/mock_json/main/conf21/contents.json")
    suspend fun getSessions(): List<Session>

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com"
    }
}