package com.survivalcoding.ifkakao.data.datasource.local

import com.survivalcoding.ifkakao.domain.model.Like
import com.survivalcoding.ifkakao.domain.repository.SessionLocalRepository

class SessionLocalDataSource(private val likeDao: LikeDao) : SessionLocalRepository {
    override suspend fun getLikes(): List<Like> = likeDao.getAll()
    override suspend fun isLiking(idx: Int): Boolean = (likeDao.getLikeById(idx) != null)
    override suspend fun addLike(like: Like) = likeDao.insert(like)
    override suspend fun deleteLike(like: Like) = likeDao.delete(like)
}
