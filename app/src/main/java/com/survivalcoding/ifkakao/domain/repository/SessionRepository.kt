package com.survivalcoding.ifkakao.domain.repository

import com.survivalcoding.ifkakao.domain.model.Session

interface SessionRepository {
    suspend fun getSessionAll(): List<Session>
    suspend fun getSessionLike(): List<Session>
    suspend fun getSessionById(id: Int): Session
    suspend fun likeSession(session: Session)
    suspend fun unlikeSession(session: Session)
    suspend fun sortByTitleAsc(): List<Session>
    suspend fun sortByTitleDesc(): List<Session>
    suspend fun sortByCompanyAsc(): List<Session>
    suspend fun sortByCompanyDesc(): List<Session>
    suspend fun sortByCategoryAsc(): List<Session>
    suspend fun sortByCategoryDesc(): List<Session>
}