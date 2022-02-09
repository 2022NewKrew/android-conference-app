package com.example.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LikeDao {
    @Query("SELECT idx FROM LikeEntity WHERE userName=:name")
    fun getAll(name: String): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(like: LikeEntity)

    @Delete
    fun delete(like: LikeEntity)
}