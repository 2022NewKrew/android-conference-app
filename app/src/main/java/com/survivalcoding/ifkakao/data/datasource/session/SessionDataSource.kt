package com.survivalcoding.ifkakao.data.datasource.session

import com.survivalcoding.ifkakao.domain.model.Session

interface SessionDataSource {
    suspend fun getSessionAll(): List<Session>
    suspend fun getSessionById(id: Int): Session
    suspend fun getSessionsByField(field: String): List<Session>
    suspend fun getSessionsRelated(id: Int, field: String): List<Session>
    suspend fun sortByTitleAsc(): List<Session>
    suspend fun sortByTitleDesc(): List<Session>
    suspend fun sortByCompanyAsc(): List<Session>
    suspend fun sortByCompanyDesc(): List<Session>
    suspend fun sortByCategoryAsc(): List<Session>
    suspend fun sortByCategoryDesc(): List<Session>
}