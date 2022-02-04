package com.survivalcoding.ifkakao.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.survivalcoding.ifkakao.domain.model.IkSessionLocalData
import kotlinx.coroutines.flow.Flow

@Dao
interface IfKakaoSessionDao {
    @Query("select * from IkSessionLocalData")
    fun getAllSession(): Flow<List<IkSessionLocalData>>

    @Query("select * from IkSessionLocalData where id = :id")
    fun getSessionById(id: Int): Flow<IkSessionLocalData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(session: IkSessionLocalData)
}