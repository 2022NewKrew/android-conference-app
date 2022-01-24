package com.survivalcoding.ifkakao.data.datasource.remote

class SessionRemoteDataSource(private val sessionApi: SessionApi) {
    suspend fun getSessions() = sessionApi.getSessions()
}
