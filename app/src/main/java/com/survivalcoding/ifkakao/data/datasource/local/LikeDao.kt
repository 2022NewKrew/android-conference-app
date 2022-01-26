package com.survivalcoding.ifkakao.data.datasource.local

import androidx.room.*
import com.survivalcoding.ifkakao.domain.model.Like

@Dao
interface LikeDao {
    @Query("SELECT * FROM `like`")
    suspend fun getAll(): List<Like>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg likes: Like)

    @Delete
    suspend fun delete(like: Like)
}