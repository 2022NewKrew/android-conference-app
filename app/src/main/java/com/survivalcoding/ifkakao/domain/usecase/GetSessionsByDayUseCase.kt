package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.IfKakaoContent
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.presentation.util.Keywords
import javax.inject.Inject

class GetSessionsByDayUseCase @Inject constructor(
    private val content: IfKakaoContent,
) {
    operator fun invoke(day: Int, count: Int, keywords: Keywords): List<IkSessionData> {
        val selectedField = keywords.fieldList.filter { it.isSelected }.map { it.text }
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

//        if (keywords.classificationList.isNotEmpty())
//            result = result.filter {
//                it.tag.any { tag -> tag.text in keywords.classificationList.map { it.text } }
//            }

        return result.take(count)
    }
}