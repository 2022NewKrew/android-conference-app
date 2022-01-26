package com.survivalcoding.ifkakao.domain.repository

import com.survivalcoding.ifkakao.domain.model.Like

interface SessionLocalRepository {
    suspend fun getLikes(): List<Like>
    suspend fun addLike(like: Like)
    suspend fun deleteLike(like: Like)
}