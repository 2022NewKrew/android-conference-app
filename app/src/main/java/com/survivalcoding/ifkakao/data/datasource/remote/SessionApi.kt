package com.survivalcoding.ifkakao.data.datasource.remote

import com.survivalcoding.ifkakao.domain.model.IfKakaoData
import retrofit2.http.GET

interface SessionApi {
    @GET("junsuk5/mock_json/main/conf21/contents.json")
    suspend fun getIfKakaoData(): IfKakaoData

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com"
    }
}