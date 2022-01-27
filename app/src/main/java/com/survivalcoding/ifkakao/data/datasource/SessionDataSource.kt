package com.survivalcoding.ifkakao.data.datasource

import com.survivalcoding.ifkakao.domain.model.SessionItem

interface SessionDataSource {
    suspend fun getSessionList(): List<SessionItem>?
    suspend fun getSessionItem(idx: Int): SessionItem?
}