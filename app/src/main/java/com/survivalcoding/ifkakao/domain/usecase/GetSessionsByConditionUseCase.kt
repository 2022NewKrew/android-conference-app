package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.IfKakaoContent
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.presentation.util.Keywords
import javax.inject.Inject

class GetSessionsByConditionUseCase @Inject constructor(
    private val content: IfKakaoContent,
) {
    operator fun invoke(day: Int, count: Int, keywords: Keywords): List<IkSessionData> {
        val selectedField = keywords.fieldList.filter { it.isSelected }.map { it.text }
        val selectedClass = keywords.classificationList.filter { it.isSelected }.map { it.text }
        val selectedTech = keywords.techClassificationList.filter { it.isSelected }.map { it.text }
        val selectedCompany = keywords.companyList.filter { it.isSelected }.map { it.text }

        var result = content.data.filter {
            when (day) {
                1 -> it.exposureDay == 1
                2 -> it.exposureDay == 2
                else -> {
                    true
                }
            }
        }
        if (selectedField.isNotEmpty())
            result = result.filter { it.field in selectedField }

        if (selectedClass.isNotEmpty()) {
            result = result.filter { it.tag.any { tag -> tag.text in selectedClass } }
        }

        if (selectedTech.isNotEmpty()) {
            result = result.filter { it.tag.any { tag -> tag.text in selectedTech } }
        }

        if (selectedCompany.isNotEmpty()) {
            result = result.filter { it.company in selectedCompany }
        }

        return result.take(count)
    }
}