package com.survivalcoding.ifkakao.data.repository

import com.survivalcoding.ifkakao.domain.model.IfKakaoData
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.repository.SessionLocalRepository
import com.survivalcoding.ifkakao.domain.repository.SessionRemoteRepository
import com.survivalcoding.ifkakao.domain.repository.SessionRepository

class SessionRepositoryImpl(
    private val sessionRemoteDataSource: SessionRemoteRepository,
    private val sessionLocalDataSource: SessionLocalRepository
) : SessionRepository {
    override suspend fun getIfKakaoData(): IfKakaoData = sessionRemoteDataSource.getIfKakaoData()
    override suspend fun getLikes(): List<Session> = sessionLocalDataSource.getLikes()
    override suspend fun addLike(session: Session) = sessionLocalDataSource.addLike(session)
    override suspend fun deleteLike(session: Session) = sessionLocalDataSource.deleteLike(session)
}