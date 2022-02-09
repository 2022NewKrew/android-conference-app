package com.survivalcoding.ifkakao.data.repository

import com.survivalcoding.ifkakao.data.datasource.SessionDataSource
import com.survivalcoding.ifkakao.domain.model.SessionItem
import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(private val sessionDataSource: SessionDataSource) :
    SessionRepository {
    override suspend fun getSessionList(): List<SessionItem>? {
        return sessionDataSource.getSessionList()
    }

    override suspend fun getSessionItem(idx: Int): SessionItem? {
        return sessionDataSource.getSessionItem(idx)
    }
}