package com.survivalcoding.ifkakao.data.datasource.local

import androidx.room.*
import com.survivalcoding.ifkakao.domain.model.Session

@Dao
interface LikeDao {
    @Query("SELECT * FROM session")
    suspend fun getAll(): List<Session>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg session: Session)

    @Delete
    suspend fun delete(session: Session)
}