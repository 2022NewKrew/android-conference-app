package com.survivalcoding.ifkakao.domain.repository

import com.survivalcoding.ifkakao.domain.model.IfKakaoData
import com.survivalcoding.ifkakao.domain.model.Session

interface SessionRepository : SessionLocalRepository, SessionRemoteRepository {
    override suspend fun getIfKakaoData(): IfKakaoData
    override suspend fun getLikes(): List<Session>
    override suspend fun addLike(session: Session)
    override suspend fun deleteLike(session: Session)
}