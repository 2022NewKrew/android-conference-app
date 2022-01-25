package com.example.data.network

import com.example.domain.entity.Conference
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface IfKakaoService {
    @GET("/junsuk5/mock_json/main/conf21/contents.json")
    suspend fun getConferences(): Conference
}