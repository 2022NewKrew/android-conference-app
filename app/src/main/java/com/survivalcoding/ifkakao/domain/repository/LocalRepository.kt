package com.survivalcoding.ifkakao.domain.repository

import com.survivalcoding.ifkakao.domain.model.IkSessionLocalData
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    fun getAllSessionLocalData(): Flow<List<IkSessionLocalData>>
    fun getSessionLocalDataById(id: Int): Flow<IkSessionLocalData>
    suspend fun insertSessionLocalData(session: IkSessionLocalData)
}