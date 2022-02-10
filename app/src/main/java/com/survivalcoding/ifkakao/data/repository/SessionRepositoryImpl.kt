package com.survivalcoding.ifkakao.data.repository

import com.survivalcoding.ifkakao.data.datasource.like.LikeDataSource
import com.survivalcoding.ifkakao.data.datasource.session.SessionDataSource
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionDataSource: SessionDataSource,
    private val likeDataSource: LikeDataSource
) : SessionRepository {
    override suspend fun getSessionAll(): List<Session> = sessionDataSource.getSessionAll()

    override suspend fun getSessionLike(): List<Session> = likeDataSource.getSessionLike()

    override suspend fun getSessionById(id: Int): Session = sessionDataSource.getSessionById(id)

    override suspend fun getSessionsByField(field: String): List<Session> = sessionDataSource.getSessionsByField(field)

    override suspend fun searchSessions(
        day: String,
        fields: MutableList<String>,
        keywords: MutableList<String>,
        companies: MutableList<String>
    ): List<Session> = sessionDataSource.searchSessions(day, fields, keywords, companies)

    override suspend fun getSessionsRelated(id: Int, field: String): List<Session> = sessionDataSource.getSessionsRelated(id, field)

    override suspend fun likeSession(session: Session) = likeDataSource.likeSession(session)

    override suspend fun unlikeSession(session: Session) = likeDataSource.unlikeSession(session)

    override suspend fun checkLike(id: Int): Boolean = likeDataSource.checkLike(id)

    override suspend fun sortByTitleAsc(): List<Session> = sessionDataSource.sortByTitleAsc()

    override suspend fun sortByTitleDesc(): List<Session> = sessionDataSource.sortByTitleDesc()

    override suspend fun sortByCompanyAsc(): List<Session> = sessionDataSource.sortByCompanyAsc()

    override suspend fun sortByCompanyDesc(): List<Session> = sessionDataSource.sortByCompanyDesc()

    override suspend fun sortByCategoryAsc(): List<Session> = sessionDataSource.sortByCategoryAsc()

    override suspend fun sortByCategoryDesc(): List<Session> = sessionDataSource.sortByCategoryDesc()
}