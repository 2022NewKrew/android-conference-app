package com.survivalcoding.ifkakao.data.dto

import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.model.IkSessionSpeaker
import com.survivalcoding.ifkakao.domain.model.IkSessionVideo

internal fun IkSessionDataDTO.toEntity(): IkSessionData {
    return IkSessionData(
        company = company,
        content = content,
        field = field,
        id = idx,
        sessionTag = contentTag.split("  "),
        sessionSpeakers = contentsSpeakerList.indices.map {
            val sessionSpeakerDTO = contentsSpeakerList[it]
            val speakerProfileDTO = linkList.SPEAKER_PROFILE[it]
            IkSessionSpeaker(
                id = sessionSpeakerDTO.idx,
                company = sessionSpeakerDTO.company,
                name = "${sessionSpeakerDTO.nameKo} ${sessionSpeakerDTO.nameKo}",
                occupation = sessionSpeakerDTO.occupation,
                imageUrl = speakerProfileDTO.url,
            )
        },
        title = title,
        video = with(linkList) {
            IkSessionVideo(
                videoLength = VIDEO.first().description,
                videoUrl = VIDEO.first().url,
                thumbnailUrl = PC_IMAGE.first().url,
            )
        },
        isSpotlight = spotlightYn == "Y",
        isVideo = linkList.VIDEO.first().description != null
    )
}