package com.survivalcoding.ifkakao.data.datasource.local

import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.repository.SessionLocalRepository

class SessionLocalDataSource(private val likeDao: LikeDao) : SessionLocalRepository {
    override suspend fun getLikes(): List<Session> = likeDao.getAll()
    override suspend fun addLike(session: Session) = likeDao.insert(session)
    override suspend fun deleteLike(session: Session) = likeDao.delete(session)
}
