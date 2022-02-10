package com.survivalcoding.ifkakao.data.datasource.session.remote

import com.survivalcoding.ifkakao.data.datasource.session.SessionDataSource
import com.survivalcoding.ifkakao.data.datasource.session.SessionResponse
import com.survivalcoding.ifkakao.data.datasource.session.remote.service.SessionService
import com.survivalcoding.ifkakao.domain.model.LinkList
import com.survivalcoding.ifkakao.domain.model.RelationList
import com.survivalcoding.ifkakao.domain.model.Session

class SessionRemoteDataSource(private val service: SessionService) : SessionDataSource {

    private lateinit var sessions: SessionResponse

    override suspend fun getSessionAll(): List<Session> {
        sessions = service.getSessions()
        return sessions.data
    }

    override suspend fun getSessionById(id: Int): Session =
        sessions.data.find { it.idx == id } ?: Session(
            -1,
            "",
            "",
            "",
            "",
            "",
            emptyList(),
            "",
            -1,
            "",
            "",
            -1,
            "",
            -1,
            LinkList(emptyList(), emptyList(), emptyList(), emptyList()),
            "",
            RelationList(emptyList(), emptyList(), emptyList()),
            "",
            "",
            -1,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        )

    override suspend fun getSessionsByField(field: String): List<Session> =
        sessions.data.filter { it.field == field }

    override suspend fun searchSessions(
        day: String,
        fields: MutableList<String>,
        keywords: MutableList<String>,
        companies: MutableList<String>
    ): List<Session> =
        sessions.data.filter { session ->
            // day
            (day.isBlank() || session.relationList.MAIN_EXPOSURE_DAY.isNotEmpty() && session.relationList.MAIN_EXPOSURE_DAY[0] == day)
                    // field
                    && (fields.isEmpty() || fields.contains(session.field))
                    // keywords
                    && (keywords.isEmpty() || keywords.any {
                session.relationList.CLASSIFICATION.contains(
                    it
                )
            })
                    // company
                    && (companies.isEmpty() || companies.contains(session.companyName))
        }

    override suspend fun getSessionsRelated(id: Int, field: String): List<Session> =
        sessions.data.filter { it.field == field && it.idx != id }

    override suspend fun sortByTitleAsc(): List<Session> =
        sessions.data.sortedBy { it.title }

    override suspend fun sortByTitleDesc(): List<Session> =
        sessions.data.sortedByDescending { it.title }

    override suspend fun sortByCompanyAsc(): List<Session> =
        sessions.data.sortedBy { it.companyName }

    override suspend fun sortByCompanyDesc(): List<Session> =
        sessions.data.sortedByDescending { it.companyName }

    override suspend fun sortByCategoryAsc(): List<Session> =
        sessions.data.sortedBy { it.categoryIdx }

    override suspend fun sortByCategoryDesc(): List<Session> =
        sessions.data.sortedByDescending { it.categoryIdx }
}