package com.survivalcoding.ifkakao.data.dto

import com.survivalcoding.ifkakao.domain.model.*

internal fun IkContentDTO.toEntity(type: IkContentType): List<IkContent>? {
    return if (success.not()) null
    else {
        when (type) {
            IkContentType.LIST_ITEM -> {
                data.map {
                    IkListItem(
                        imageUrl = it.linkList.MO_IMAGE.first().url,
                        videoLength = it.linkList.VIDEO.first().description,
                        company = it.company,
                        field = it.field,
                        title = it.title,
                        isSpotlight = it.spotlightYn == "Y",
                        exposureDay = it.relationList.MAIN_EXPOSURE_DAY.first()
                    )
                }
            }
            IkContentType.MAIN_ITEM -> {
                data.map {
                    IkMainItem(
                        imageUrl = it.linkList.MO_MAIN_IMAGE.first().url,
                        videoLength = it.linkList.VIDEO.first().description,
                        field = it.field,
                        company = it.company,
                        classification = it.relationList.CLASSIFICATION,
                        techClassification = it.relationList.TECH_CLASSIFICATION,
                        title = it.title,
                        content = it.content,
                        contentTag = it.contentTag,
                        speakerImageUrl = it.linkList.SPEAKER_PROFILE.first().url,
                        speaker = it.contentsSpeakerList.first().toEntity(),
                    )
                }
            }
        }
    }
}

internal fun IkContentsSpeakerDTO.toEntity(): IkSpeaker {
    return IkSpeaker(
        nameKor = nameKo,
        nameEng = nameEn,
        company = company,
        occupation = occupation,
    )
}