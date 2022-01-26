package com.survivalcoding.ifkakao.presentation

import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.model.IkTagInfo
import com.survivalcoding.ifkakao.domain.model.TagType

data class FragmentInfoSummary(
    val fragmentType: FragmentType,
    val exposedSessionsCount: Int = 4,
    val detailSessionData: IkSessionData = IkSessionData.getEmptySessionData(),
    val keywordSelectedKeyword: IkTagInfo = IkTagInfo(TagType.COMPANY, "카카오"),
    val byDaySelectedDay: Int = 3,
) {
    fun getInformation(): FragmentInformation {
        return when (fragmentType) {
            FragmentType.HIGHLIGHT -> HighlightFragmentInformation()
            FragmentType.DETAIL -> DetailFragmentInformation(
                detailSessionData = detailSessionData,
                exposedSessionsCount = exposedSessionsCount,
            )
            FragmentType.BY_KEYWORD -> ByKeywordFragmentInformation(
                keywordSelectedKeyword = keywordSelectedKeyword,
                exposedSessionsCount = exposedSessionsCount,
            )
            FragmentType.BY_DAY -> ByDayFragmentInformation(
                byDaySelectedDay = byDaySelectedDay,
                exposedSessionsCount = exposedSessionsCount,
            )
        }
    }
}