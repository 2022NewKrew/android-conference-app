package com.survivalcoding.ifkakao.data.datasource.service

import com.survivalcoding.ifkakao.data.dto.IkContentDTO
import retrofit2.http.GET

interface IkContentsService {
    @GET("/junsuk5/mock_json/main/conf21/contents.json")
    suspend fun getContents(): IkContentDTO
}