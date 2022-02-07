package com.survivalcoding.ifkakao.data.repository

import com.survivalcoding.ifkakao.data.datasource.IfKakaoSessionDao
import com.survivalcoding.ifkakao.domain.model.IkSessionLocalData
import com.survivalcoding.ifkakao.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class SessionLocalRepositoryImpl(private val dao: IfKakaoSessionDao) : LocalRepository {
    override fun getAllSessionLocalData(): Flow<List<IkSessionLocalData>> {
        return dao.getAllSession()
    }

    override fun getSessionLocalDataById(id: Int): Flow<IkSessionLocalData> {
        return dao.getSessionById(id)
    }

    override suspend fun insertSessionLocalData(session: IkSessionLocalData) {
        return dao.insert(session)
    }
}