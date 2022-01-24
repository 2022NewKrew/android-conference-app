package com.survivalcoding.ifkakao.data.repository

import com.survivalcoding.ifkakao.data.datasource.local.SessionLocalDataSource
import com.survivalcoding.ifkakao.data.datasource.remote.SessionRemoteDataSource
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.repository.SessionRepository

class SessionRepositoryImpl(
    private val sessionRemoteDataSource: SessionRemoteDataSource,
    private val sessionLocalDataSource: SessionLocalDataSource
) : SessionRepository {
    override fun getHighLightedSessions() {
        TODO("Not yet implemented")
    }

    override suspend fun getAllRemoteSessions(): List<Session> = sessionRemoteDataSource.getSessions()


    override fun getMySessions() {
        TODO("Not yet implemented")
    }
}