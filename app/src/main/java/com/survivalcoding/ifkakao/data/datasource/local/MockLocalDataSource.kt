package com.survivalcoding.ifkakao.data.datasource.local

import com.survivalcoding.ifkakao.domain.model.Like
import com.survivalcoding.ifkakao.domain.repository.SessionLocalRepository

class MockLocalDataSource : SessionLocalRepository {
    private var likes = listOf(
        Like(123),
        Like(20)
    )

    override suspend fun getLikes(): List<Like> = likes
    override suspend fun isLiking(idx: Int): Boolean {
        for (like in likes) {
            if (like.idx == idx) return true
        }
        return false
    }

    override suspend fun addLike(like: Like) {
        if (!likes.contains(like)) likes = likes.plus(like)
    }

    override suspend fun deleteLike(like: Like) {
        likes = likes.minus(like)
    }
}