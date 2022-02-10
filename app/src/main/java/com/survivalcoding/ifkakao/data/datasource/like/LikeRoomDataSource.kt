package com.survivalcoding.ifkakao.data.datasource.like

import com.survivalcoding.ifkakao.domain.model.Session
import javax.inject.Inject

class LikeRoomDataSource(private val dao: LikeDao) : LikeDataSource {
    override suspend fun getSessionLike(): List<Session> = dao.getSessionLike()

    override suspend fun likeSession(session: Session) = dao.likeSession(session)

    override suspend fun unlikeSession(session: Session) = dao.unlikeSession(session)

    override suspend fun checkLike(id: Int): Boolean = dao.checkLike(id)
}