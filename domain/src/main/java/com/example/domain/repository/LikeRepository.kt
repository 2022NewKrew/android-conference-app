package com.example.domain.repository

import com.example.domain.entity.Like

interface LikeRepository {
    suspend fun getAll(id: String): List<Int>?
}