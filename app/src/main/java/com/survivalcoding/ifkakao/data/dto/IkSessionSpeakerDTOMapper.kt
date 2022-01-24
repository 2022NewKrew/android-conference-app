package com.survivalcoding.ifkakao.data.dto

import com.survivalcoding.ifkakao.domain.model.IkSessionSpeaker

internal fun IkSessionSpeakerDTO?.toEntity(): IkSessionSpeaker {
    if (this == null) return IkSessionSpeaker(isValid = false)
    return IkSessionSpeaker(
        nameEn = nameEn ?: "",
        nameKo = nameKo ?: "",
        occupation = occupation ?: "",
        company = company ?: "",
    )
}