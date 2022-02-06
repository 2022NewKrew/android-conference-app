package com.example.data.db

import androidx.room.*

@Dao
interface LikeDao {
    @Query("SELECT * FROM LikeEntity WHERE :name")
    fun getAll(name: String): List<LikeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(like: LikeEntity)

    @Delete
    fun delete(like: LikeEntity)
}