package com.survivalcoding.ifkakao.presentation

import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.model.IkTagInfo

data class FragmentReplaceInfo(
    val session: IkSessionData? = null,
    val exposeCount: Int = 4,
    val scrollX: Int = 0,
    val scrollY: Int = 0,
)