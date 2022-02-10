package com.survivalcoding.ifkakao.data.datasource.like

import com.survivalcoding.ifkakao.domain.model.Session
import javax.inject.Inject

class LikeInMemoryDataSource @Inject constructor() : LikeDataSource {

    private var sessions: MutableList<Session> = mutableListOf()

    override suspend fun getSessionLike(): List<Session> = sessions

    override suspend fun likeSession(session: Session) {
        if (!sessions.contains(session)) sessions.add(session)
    }

    override suspend fun unlikeSession(session: Session) {
        sessions.remove(session)
    }

    override suspend fun checkLike(id: Int): Boolean = sessions.find { it.idx == id } != null
}