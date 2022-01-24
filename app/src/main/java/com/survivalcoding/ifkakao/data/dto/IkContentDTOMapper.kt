package com.survivalcoding.ifkakao.data.dto

import com.survivalcoding.ifkakao.domain.model.*

internal fun IkContentDTO.toEntity(type: IkContentType): List<IkContent>? {
    return if (success.not()) null
    else {
        when (type) {
            IkContentType.LIST_ITEM -> {
                data.map {
                    IkListItem(
                        id = it.idx,
                        images = it.linkList.PC_IMAGE.map { pcImage -> pcImage.toEntity() },
                        videos = it.linkList.VIDEO.map { video -> video.toEntity() },
                        company = it.company,
                        field = it.field,
                        title = it.title,
                        isSpotlight = it.spotlightYn == "Y",
                        exposureDay = it.relationList.MAIN_EXPOSURE_DAY
                    )
                }
            }
            IkContentType.MAIN_ITEM -> {
                data.map {
                    IkMainItem(
                        id = it.idx,
                        images = it.linkList.PC_IMAGE.map { pcImage -> pcImage.toEntity() },
                        videos = it.linkList.VIDEO.map { video -> video.toEntity() },
                        field = it.field,
                        company = it.company,
                        classification = it.relationList.CLASSIFICATION,
                        techClassification = it.relationList.TECH_CLASSIFICATION,
                        title = it.title,
                        content = it.content,
                        contentTag = it.contentTag,
                        speakerProfiles = it.linkList.SPEAKER_PROFILE.map { sp -> sp.toEntity() },
                        speakerList = it.contentsSpeakerList.map { csl -> csl.toEntity() },
                    )
                }
            }
        }
    }
}

internal fun IkSpeakerProfileDTO.toEntity(): IkSpeakerProfile {
    return IkSpeakerProfile(imageUrl = url)
}

internal fun IkContentsSpeakerDTO.toEntity(): IkSpeaker {
    return IkSpeaker(
        nameKor = nameKo,
        nameEng = nameEn,
        company = company,
        occupation = occupation,
    )
}

internal fun IkPCImageDTO.toEntity(): IkPCImage {
    return IkPCImage(url = url)
}

internal fun IkVideoDTO.toEntity(): IkVideo {
    return IkVideo(
        url = url,
        videoLength = description,
    )
}