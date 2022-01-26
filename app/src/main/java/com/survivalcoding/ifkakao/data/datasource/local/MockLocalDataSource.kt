package com.survivalcoding.ifkakao.data.datasource.local

import com.survivalcoding.ifkakao.domain.model.Like
import com.survivalcoding.ifkakao.domain.repository.SessionLocalRepository

class MockLocalDataSource : SessionLocalRepository {
    var nextId = 3
    private var likes = listOf(
        Like(123,1),
        Like(20, 2)
    )

    override suspend fun getLikes(): List<Like> = likes
    override suspend fun isLiking(idx: Int): Boolean {
        for (like in likes) {
            if (like.idx == idx) return true
        }
        return false
    }

    override suspend fun addLike(like: Like) {
        likes = likes.plus(like.copy(id = nextId))
        nextId += 1
    }

    override suspend fun deleteLike(like: Like) {
        likes = likes.minus(like)
    }
}