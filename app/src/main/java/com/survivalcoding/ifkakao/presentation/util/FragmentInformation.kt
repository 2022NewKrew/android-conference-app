package com.survivalcoding.ifkakao.presentation.util

import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.model.IkTagInfo

data class FragmentInformation(
    val session: IkSessionData = IkSessionData.getEmptySessionData(),
    val exposedListCount: Int = 4,
    val selectedKeyword: IkTagInfo = IkTagInfo.getEmptyTagInfo(),
    val selectedDay: Int = 3,
)