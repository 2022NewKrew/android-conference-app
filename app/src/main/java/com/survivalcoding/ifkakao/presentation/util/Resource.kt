package com.survivalcoding.ifkakao.presentation.util

import kotlinx.coroutines.flow.MutableStateFlow

class Resource {
    val highlightBackgroundMobile =
        "https://t1.kakaocdn.net/service_if_kakao_prod/images/mo/bg_bye_2021.png"
    val highlightBackgroundPc =
        "https://t1.kakaocdn.net/service_if_kakao_prod/images/pc/bg_bye_2021.png"
    val highlightHandIcon =
        "https://t1.kakaocdn.net/service_if_kakao_prod/images/ico_bye_2021.gif"
    val sessionBackgroundMobile =
        "https://i.ibb.co/Bg3F0hJ/vod-teaser-2021-mobile.gif"
    val sessionBackgroundPc =
        "https://i.ibb.co/LZHJt3f/vod-teaser-2021-pc.gif"

    val fieldList = MutableStateFlow(listOf("서비스", "비즈니스", "기술"))
    val classificationList = listOf(
        "플랫폼", "커머스", "B2B", "구독", "광고&마케팅", "핀테크", "디지털자산", "콘텐츠",
        "크리에이터", "ESG", "파트너성장", "소셜임팩트"
    )
    val techClassificationList = listOf(
        "백엔드", "머신러닝/AI", "데이터", "클라우드", "인프라/DevOps", "블록체인", "지식그래프", "모바일",
        "Android", "iOS", "웹/프론트엔드", "IoT", "오픈소스", "개발문화", "기타"
    )
    val companyList = listOf(
        "카카오", "카카오게임즈", "카카오모빌리티", "카카오뱅크", "카카오브레인", "카카오스타일", "카카오엔터테인먼트",
        "카카오엔터프라이즈", "카카오임팩트", "카카오페이", "카카오커머스", "그라운드X"
    )
}