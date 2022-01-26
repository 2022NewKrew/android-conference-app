package com.survivalcoding.ifkakao.presentation

import com.survivalcoding.ifkakao.domain.model.IkSessionData

data class FragmentInformation(
    val relatedSessionsCount: Int = 4,
    val currentSession: IkSessionData = IkSessionData.getEmptySessionData(),
)