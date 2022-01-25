package com.survivalcoding.ifkakao.data.datasource.like

import com.survivalcoding.ifkakao.domain.model.Session

interface LikeDataSource {
    suspend fun getSessionLike(): List<Session>
    suspend fun likeSession(session: Session)
    suspend fun unlikeSession(session: Session)
}