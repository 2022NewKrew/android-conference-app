package com.example.data.repositoryImpl

import com.example.data.db.LikeDao
import com.example.data.db.LikeDatabase
import com.example.data.db.LikeEntity
import com.example.domain.entity.Like
import com.example.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(private val dao: LikeDao) : LikeRepository {
    override fun getAll(id: String): Flow<List<Int>> = dao.getAll(id)
    override suspend fun addLike(id: String, idx: Int) {
        println(LikeEntity(id, idx))
        dao.insert(LikeEntity(id, idx))
    }

    override suspend fun deleteLike(id: String, idx: Int) {
        dao.delete(LikeEntity(id, idx))
    }
}