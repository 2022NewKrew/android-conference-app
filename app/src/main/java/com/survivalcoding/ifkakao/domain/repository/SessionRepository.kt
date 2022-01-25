package com.survivalcoding.ifkakao.domain.repository

import com.survivalcoding.ifkakao.domain.model.IfKakaoData
import com.survivalcoding.ifkakao.domain.model.Like
import com.survivalcoding.ifkakao.domain.model.Session


interface SessionRepository : SessionLocalRepository, SessionRemoteRepository {
    override suspend fun getIfKakaoData(): IfKakaoData
    override suspend fun getLikes(): List<Like>
    override suspend fun addLike(like: Like)
    override suspend fun deleteLike(like: Like)
}