package com.survivalcoding.ifkakao.data.datasource.local

import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.repository.SessionLocalRepository

class MockLocalDataSource : SessionLocalRepository {
    override suspend fun getLikes(): List<Session> = emptyList()
    override suspend fun addLike(session: Session) {}
    override suspend fun deleteLike(session: Session) {}
}