package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.entity.Categories
import com.survivalcoding.ifkakao.domain.entity.Category
import com.survivalcoding.ifkakao.domain.entity.Session
import com.survivalcoding.ifkakao.domain.entity.SortBy
import com.survivalcoding.ifkakao.domain.repository.IfKakaoRepository
import javax.inject.Inject

class GetSelectedSessionsUseCase @Inject constructor(private val ifKakaoRepository: IfKakaoRepository) {
    suspend operator fun invoke(
        day: Int = 3,
        categories: Categories = Categories(),
        sortBy: SortBy = SortBy.TIME
    ): List<Session> {
        return ifKakaoRepository.getAllSessions().filter { session ->
            if (day == 3) true // Day3(All)
            else session.exposureDay == day
        }.filter { session ->
            filterByCategory(categories, session.categories)
        }.sortedBy {
            when (sortBy) {
                SortBy.TIME -> it.exposureDay.toString()
                SortBy.TITLE -> it.title
            }
        }
    }

    private fun filterByCategory(
        selectedCategories: Categories,
        sessionCategories: Categories
    ): Boolean {
        if (isNotMatch(selectedCategories.business, sessionCategories.business)) return false
        if (isNotMatch(selectedCategories.company, sessionCategories.company)) return false
        if (isNotMatch(selectedCategories.field, sessionCategories.field)) return false
        if (isNotMatch(selectedCategories.tech, sessionCategories.tech)) return false

        return true
    }

    private fun isNotMatch(
        selectedCategoryList: List<Category>,
        sessionCategoryList: List<Category>
    ): Boolean {
        selectedCategoryList.any {
            it in sessionCategoryList
        }.let { any ->
            if (!any && selectedCategoryList.isNotEmpty()) return true
        }
        return false
    }
}