package com.survivalcoding.ifkakao.domain.repository

import com.survivalcoding.ifkakao.domain.model.SessionItem

interface SessionRepository {
    suspend fun getSessionList(): List<SessionItem>?
    suspend fun getSessionItem(idx: Int): SessionItem?
}