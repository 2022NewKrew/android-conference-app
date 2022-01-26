package com.survivalcoding.ifkakao.presentation

import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.model.IkTagInfo

abstract class FragmentInformation {
    abstract val fragmentType: FragmentType
    abstract val exposedSessionsCount: Int
}

class HighlightFragmentInformation : FragmentInformation() {
    override val fragmentType = FragmentType.HIGHLIGHT
    override val exposedSessionsCount = Int.MAX_VALUE
}

class DetailFragmentInformation(
    val detailSessionData: IkSessionData,
    override val exposedSessionsCount: Int = 4,
) : FragmentInformation() {
    override val fragmentType = FragmentType.DETAIL
}

class ByKeywordFragmentInformation(
    val keywordSelectedKeyword: IkTagInfo,
    override val exposedSessionsCount: Int = 8,
) : FragmentInformation() {
    override val fragmentType = FragmentType.BY_KEYWORD
}

class ByDayFragmentInformation(
    val byDaySelectedDay: Int,
    override val exposedSessionsCount: Int = 8,
) : FragmentInformation() {
    override val fragmentType = FragmentType.BY_DAY
}