package com.survivalcoding.ifkakao.data.dto

import com.survivalcoding.ifkakao.domain.model.IkLinkLists

internal fun IkLinkListsDTO?.toEntity(): IkLinkLists {
    if (this == null) return IkLinkLists(isValid = false)
    return IkLinkLists(
        pcImage = PC_IMAGE?.map { it.toEntity() } ?: listOf(),
        video = VIDEO?.map { it.toEntity() } ?: listOf(),
        speakerProfile = SPEAKER_PROFILE?.map { it.toEntity() } ?: listOf()
    )
}