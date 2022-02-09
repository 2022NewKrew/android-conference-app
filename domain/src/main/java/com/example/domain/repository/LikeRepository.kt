package com.example.domain.repository

import com.example.domain.entity.Like
import kotlinx.coroutines.flow.Flow

interface LikeRepository {
    fun getAll(id: String): Flow<List<Int>>
    suspend fun addLike(id: String, idx: Int)
    suspend fun deleteLike(id: String, idx: Int)
}