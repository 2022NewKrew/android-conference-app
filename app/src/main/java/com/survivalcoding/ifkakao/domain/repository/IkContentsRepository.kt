package com.survivalcoding.ifkakao.domain.repository

import com.survivalcoding.ifkakao.domain.model.IkListItem
import com.survivalcoding.ifkakao.domain.model.IkMainItem
import kotlinx.coroutines.flow.Flow

interface IkContentsRepository {
    fun getListItems(): Flow<List<IkListItem>>
    fun getMainItems(): Flow<List<IkMainItem>>
}