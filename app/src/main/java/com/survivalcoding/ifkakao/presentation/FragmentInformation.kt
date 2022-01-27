package com.survivalcoding.ifkakao.presentation

import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.model.IkTagInfo

data class FragmentInformation(
    val relatedSessionsCount: Int = 4,
    val currentSession: IkSessionData = IkSessionData.getEmptySessionData(),
    val selectedKeyword: IkTagInfo = IkTagInfo.getEmptyTagInfo(),
)