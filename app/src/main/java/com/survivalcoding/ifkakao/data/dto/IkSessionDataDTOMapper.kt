package com.survivalcoding.ifkakao.data.dto

import com.survivalcoding.ifkakao.domain.model.IkLinkLists
import com.survivalcoding.ifkakao.domain.model.IkSessionData

internal fun IkSessionDataDTO.toEntity(): IkSessionData {
    return IkSessionData(
        idx = idx ?: -1,
        company = company ?: "",
        title = title ?: "",
        content = content ?: "",
        contentTag = contentTag ?: "",
        sessionSpeakers = contentsSpeakerList?.map { it.toEntity() } ?: listOf(),
        field = field ?: "",
        spotlightYn = spotlightYn ?: "",
        videoYn = videoYn ?: "",
        linkLists = linkList.toEntity(),
        relationLists = relationList.toEntity(),
    )
}