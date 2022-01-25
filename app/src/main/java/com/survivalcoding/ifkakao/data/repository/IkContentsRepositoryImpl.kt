package com.survivalcoding.ifkakao.data.repository

import com.survivalcoding.ifkakao.data.datasource.IkContentsDataSource
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.repository.IkContentsRepository

//class IkContentsRepositoryImpl(
//    private val ikContentsDataSource: IkContentsDataSource
//) : IkContentsRepository {
//    override suspend fun getSessions(): List<IkSessionData> {
//        return ikContentsDataSource.getContents().data.map {
//            it.toEntity()
//        }
//    }
//}