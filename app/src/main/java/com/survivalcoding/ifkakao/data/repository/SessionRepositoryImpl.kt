package com.survivalcoding.ifkakao.data.repository

import com.survivalcoding.ifkakao.data.datasource.local.SessionLocalDataSource
import com.survivalcoding.ifkakao.data.datasource.remote.SessionRemoteDataSource
import com.survivalcoding.ifkakao.domain.model.IfKakaoData
import com.survivalcoding.ifkakao.domain.repository.SessionRepository

class SessionRepositoryImpl(
    private val sessionRemoteDataSource: SessionRemoteDataSource,
    private val sessionLocalDataSource: SessionLocalDataSource
) : SessionRepository {
    override suspend fun getIfKakaoData(): IfKakaoData = sessionRemoteDataSource.getIfKakaoData()

    override fun getMySessions() {
        TODO("Not yet implemented")
    }
}