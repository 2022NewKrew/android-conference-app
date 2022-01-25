package com.survivalcoding.ifkakao.data.datasource

import com.survivalcoding.ifkakao.domain.model.Content
import retrofit2.http.GET

interface IfKakaoService {

    @GET("junsuk5/mock_json/main/conf21/contents.json")
    suspend fun getContent(): Content
}