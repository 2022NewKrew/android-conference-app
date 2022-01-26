package com.survivalcoding.ifkakao.data.repository

import com.survivalcoding.ifkakao.domain.model.IfKakaoData
import com.survivalcoding.ifkakao.domain.model.Like
import com.survivalcoding.ifkakao.domain.repository.SessionLocalRepository
import com.survivalcoding.ifkakao.domain.repository.SessionRemoteRepository
import com.survivalcoding.ifkakao.domain.repository.SessionRepository

class SessionRepositoryImpl(
    private val sessionRemoteDataSource: SessionRemoteRepository,
    private val sessionLocalDataSource: SessionLocalRepository
) : SessionRepository {
    override suspend fun getIfKakaoData(): IfKakaoData = sessionRemoteDataSource.getIfKakaoData()
    override suspend fun getLikes(): List<Like> = sessionLocalDataSource.getLikes()
    override suspend fun isLiking(idx: Int): Boolean = sessionLocalDataSource.isLiking(idx)
    override suspend fun addLike(like: Like) = sessionLocalDataSource.addLike(like)
    override suspend fun deleteLike(like: Like) = sessionLocalDataSource.deleteLike(like)
}