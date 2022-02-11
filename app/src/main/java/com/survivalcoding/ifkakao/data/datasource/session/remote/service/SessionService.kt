package com.survivalcoding.ifkakao.data.datasource.session.remote.service

import com.survivalcoding.ifkakao.data.datasource.session.SessionResponse
import retrofit2.http.GET

interface SessionService {
    @GET("/junsuk5/mock_json/main/conf21/contents.json")
    suspend fun getSessions(): SessionResponse
}