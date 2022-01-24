package com.survivalcoding.ifkakao.domain.repository

import com.survivalcoding.ifkakao.domain.model.Session

interface SessionRepository {
    fun getHighLightedSessions()
    suspend fun getAllRemoteSessions(): List<Session>
    fun getMySessions()
}