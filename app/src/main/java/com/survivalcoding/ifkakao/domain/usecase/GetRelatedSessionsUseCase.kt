package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.Session

class GetRelatedSessionsUseCase(private val getSessionsUseCase: GetSessionsUseCase) {
    suspend operator fun invoke(
        field: String,
        classification: List<String>,
        idx: Int
    ): List<Session> =
        getSessionsUseCase.invoke().filter {
            it.idx != idx && it.field == field && isInSameClassification(
                classification,
                it.relationList.TECH_CLASSIFICATION
            )
        }

    private fun isInSameClassification(
        compare: List<String>,
        subject: List<String>
    ): Boolean {
        if (compare.isEmpty()) return true
        for (c in compare) if (c in subject) return true
        return false
    }
}