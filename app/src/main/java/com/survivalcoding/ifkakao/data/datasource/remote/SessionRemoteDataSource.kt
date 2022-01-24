package com.survivalcoding.ifkakao.data.datasource.remote

import com.survivalcoding.ifkakao.domain.repository.SessionRemoteRepository

class SessionRemoteDataSource(private val sessionApi: SessionApi) : SessionRemoteRepository{
    override suspend fun getIfKakaoData() = sessionApi.getIfKakaoData()
}
