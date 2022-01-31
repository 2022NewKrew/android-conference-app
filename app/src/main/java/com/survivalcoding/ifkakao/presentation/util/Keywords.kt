package com.survivalcoding.ifkakao.presentation.util

import com.survivalcoding.ifkakao.domain.model.IkKeyword

data class Keywords(
    val fieldList: List<IkKeyword> = listOf("서비스", "비즈니스", "기술").mapIndexed { index, s ->
        IkKeyword(index, s, false)
    },
    val classificationList: List<IkKeyword> = listOf(
        "플랫폼", "커머스", "B2B", "구독", "광고&마케팅", "핀테크", "디지털자산", "콘텐츠",
        "크리에이터", "ESG", "파트너성장", "소셜임팩트"
    ).mapIndexed { index, s ->
        IkKeyword(index, s, false)
    },
    val techClassificationList: List<IkKeyword> = listOf(
        "백엔드", "머신러닝/AI", "데이터", "클라우드", "인프라/DevOps", "블록체인", "지식그래프", "모바일",
        "Android", "iOS", "웹/프론트엔드", "IoT", "오픈소스", "개발문화", "기타"
    ).mapIndexed { index, s ->
        IkKeyword(index, s, false)
    },
    val companyList: List<IkKeyword> = listOf(
        "카카오", "카카오게임즈", "카카오모빌리티", "카카오뱅크", "카카오브레인", "카카오스타일", "카카오엔터테인먼트",
        "카카오엔터프라이즈", "카카오임팩트", "카카오페이", "카카오커머스", "그라운드X"
    ).mapIndexed { index, s ->
        IkKeyword(index, s, false)
    },
)

fun List<IkKeyword>.toggle(keyword: IkKeyword): List<IkKeyword> {
    val target = first { it.id == keyword.id }
    val toggle = keyword.copy(isSelected = !keyword.isSelected)
    return minus(target).plus(toggle).sortedBy { it.id }
}