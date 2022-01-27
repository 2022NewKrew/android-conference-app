package com.survivalcoding.ifkakao.data.dto

import com.survivalcoding.ifkakao.domain.model.*

internal fun IkSessionDataDTO.toEntity(): IkSessionData {
    return IkSessionData(
        company = company,
        content = content,
        exposureDay = if (relationList.MAIN_EXPOSURE_DAY.isEmpty()) 3 else {
            if (relationList.MAIN_EXPOSURE_DAY.first() == "1Day") 1
            else 2
        },
        tag = with(relationList) {
            var idx = 0
            listOf(
                IkTagInfo(TagType.FIELD, field, ++idx),
                IkTagInfo(TagType.COMPANY, company, ++idx),
            ).plus(CLASSIFICATION.map { IkTagInfo(TagType.CLASSIFICATION, it, ++idx) })
                .plus(TECH_CLASSIFICATION.map { IkTagInfo(TagType.TECH_CLASSIFICATION, it, ++idx) })
        },
        field = field,
        id = idx,
        hashTag = contentTag,
        sessionSpeakers = contentsSpeakerList.indices.map {
            val sessionSpeakerDTO = contentsSpeakerList[it]
            val speakerProfileDTO = linkList.SPEAKER_PROFILE[it]
            IkSessionSpeaker(
                id = sessionSpeakerDTO.idx,
                company = sessionSpeakerDTO.company,
                name = "${sessionSpeakerDTO.nameKo} ${sessionSpeakerDTO.nameEn}",
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