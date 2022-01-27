package com.survivalcoding.ifkakao.presentation.detail

import com.survivalcoding.ifkakao.domain.model.IkSessionData

data class DetailUIState(
    private val relatedSessionsList: List<IkSessionData>,
    private val exposedListCount: Int,
    val session: IkSessionData?,
) {
    val exposedList = relatedSessionsList.take(exposedListCount)
    val hasMoreRelatedSessions = relatedSessionsList.size > exposedList.size
}