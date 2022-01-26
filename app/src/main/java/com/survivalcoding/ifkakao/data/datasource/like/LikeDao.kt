package com.survivalcoding.ifkakao.data.datasource.like

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.survivalcoding.ifkakao.domain.model.Session

@Dao
interface LikeDao {
    @Query("SELECT * FROM session")
    suspend fun getSessionLike(): List<Session>

    @Insert(onConflict = IGNORE)
    suspend fun likeSession(session: Session)

    @Delete
    suspend fun unlikeSession(session: Session)
}