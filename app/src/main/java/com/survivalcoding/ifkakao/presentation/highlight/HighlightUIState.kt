package com.survivalcoding.ifkakao.presentation.highlight

import com.survivalcoding.ifkakao.domain.model.IkSessionData

data class HighlightUIState(
    val highlightSessions: List<IkSessionData>,
) {
    val backgroundMobile: String =
        "https://t1.kakaocdn.net/service_if_kakao_prod/images/mo/bg_bye_2021.png"
    val icon: String = "https://t1.kakaocdn.net/service_if_kakao_prod/images/ico_bye_2021.gif"
    val backgroundPc: String =
        "https://t1.kakaocdn.net/service_if_kakao_prod/images/pc/bg_bye_2021.png"
}