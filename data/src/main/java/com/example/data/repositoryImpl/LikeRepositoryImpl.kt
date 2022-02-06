package com.example.data.repositoryImpl

import com.example.data.db.LikeDao
import com.example.data.db.LikeDatabase
import com.example.domain.entity.Like
import com.example.domain.repository.LikeRepository
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(private val dao: LikeDao) : LikeRepository {
    override suspend fun getAll(id: String): List<Int>? = dao.getAll(id)
}