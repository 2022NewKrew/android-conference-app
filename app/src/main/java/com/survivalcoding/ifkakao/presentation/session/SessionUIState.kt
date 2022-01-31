package com.survivalcoding.ifkakao.presentation.session

import com.survivalcoding.ifkakao.domain.model.IkTagInfo

data class SessionUIState(
    val day: Int = 3,
    val field: String = "",
    val company: String = "",
    val tag: List<IkTagInfo> = listOf(),
) {

}