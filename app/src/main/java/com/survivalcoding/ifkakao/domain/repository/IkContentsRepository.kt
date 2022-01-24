package com.survivalcoding.ifkakao.domain.repository

import com.survivalcoding.ifkakao.domain.model.IkContent
import com.survivalcoding.ifkakao.domain.model.IkListItem
import com.survivalcoding.ifkakao.domain.model.IkMainItem
import kotlinx.coroutines.flow.Flow

interface IkContentsRepository {
    suspend fun getListItems(): List<IkContent>?
    suspend fun getMainItems(): List<IkContent>?
}