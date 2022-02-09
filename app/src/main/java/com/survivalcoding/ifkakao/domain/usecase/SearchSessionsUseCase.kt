package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.DayType
import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import javax.inject.Inject

class SearchSessionsUseCase @Inject constructor(private val repository: SessionRepository) {
    suspend operator fun invoke(
        day: DayType,
        fields: MutableList<String>,
        keywords: MutableList<String>,
        companies: MutableList<String>
    ) = when (day) {
        DayType.Day1 -> repository.searchSessions("1Day", fields, keywords, companies)
        DayType.Day2 -> repository.searchSessions("2Day", fields, keywords, companies)
        else -> repository.searchSessions("", fields, keywords, companies)
    }
}