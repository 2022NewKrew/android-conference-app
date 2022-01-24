package com.survivalcoding.ifkakao.domain.repository

import com.survivalcoding.ifkakao.domain.model.Session

interface SessionLocalRepository {
    suspend fun getLikes(): List<Session>
    suspend fun addLike(session: Session)
    suspend fun deleteLike(session: Session)
}