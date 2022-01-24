package com.survivalcoding.ifkakao.data.repository

import com.survivalcoding.ifkakao.data.datasource.IkContentsDataSource
import com.survivalcoding.ifkakao.data.dto.toEntity
import com.survivalcoding.ifkakao.domain.model.IkContentType
import com.survivalcoding.ifkakao.domain.model.IkListItem
import com.survivalcoding.ifkakao.domain.model.IkMainItem
import com.survivalcoding.ifkakao.domain.repository.IkContentsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IkContentsRepositoryImpl(
    private val ikContentsDataSource: IkContentsDataSource
) : IkContentsRepository {
    override fun getListItems(): Flow<List<IkListItem>> {
        return flow {
            ikContentsDataSource.getContents().toEntity(IkContentType.LIST_ITEM)
        }
    }

    override fun getMainItems(): Flow<List<IkMainItem>> {
        return flow {
            ikContentsDataSource.getContents().toEntity(IkContentType.MAIN_ITEM)
        }
    }
}