package com.survivalcoding.ifkakao.data.dto

import com.survivalcoding.ifkakao.domain.model.IkInformation

internal fun IkInfoDTO?.toEntity(): IkInformation {
    if (this == null) return IkInformation(isValid = false)
    return IkInformation(
        idx = idx ?: -1,
        url = url ?: "",
        description = description ?: ""
    )
}