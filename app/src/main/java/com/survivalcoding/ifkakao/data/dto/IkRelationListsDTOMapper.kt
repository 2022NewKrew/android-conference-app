package com.survivalcoding.ifkakao.data.dto

import com.survivalcoding.ifkakao.domain.model.IkRelationLists

internal fun IkRelationListsDTO?.toEntity(): IkRelationLists {
    if (this == null) return IkRelationLists(isValid = false)
    return IkRelationLists(
        classification = CLASSIFICATION ?: listOf(),
        techClassification = TECH_CLASSIFICATION ?: listOf(),
        mainExposureDay = MAIN_EXPOSURE_DAY ?: listOf()
    )
}