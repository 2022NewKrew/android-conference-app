package com.survivalcoding.ifkakao.data.repository

import com.survivalcoding.ifkakao.data.datasource.like.LikeDataSource
import com.survivalcoding.ifkakao.data.datasource.session.SessionDataSource
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.repository.SessionRepository

class SessionRepositoryImpl(
    private val sessionDataSource: SessionDataSource,
    private val likeDataSource: LikeDataSource
) : SessionRepository {
    override suspend fun getSessionAll(): List<Session> = sessionDataSource.getSessionAll()

    override suspend fun getSessionLike(): List<Session> = likeDataSource.getSessionLike()

    override suspend fun getSessionById(id: Int): Session = sessionDataSource.getSessionById(id)

    override suspend fun likeSession(session: Session) = likeDataSource.likeSession(session)

    override suspend fun unlikeSession(session: Session) = likeDataSource.unlikeSession(session)

    override suspend fun sortByTitleAsc(): List<Session> = sessionDataSource.sortByTitleAsc()

    override suspend fun sortByTitleDesc(): List<Session> = sessionDataSource.sortByTitleDesc()

    override suspend fun sortByCompanyAsc(): List<Session> = sessionDataSource.sortByCompanyAsc()

    override suspend fun sortByCompanyDesc(): List<Session> = sessionDataSource.sortByCompanyDesc()

    override suspend fun sortByCategoryAsc(): List<Session> = sessionDataSource.sortByCategoryAsc()

    override suspend fun sortByCategoryDesc(): List<Session> = sessionDataSource.sortByCategoryDesc()
}