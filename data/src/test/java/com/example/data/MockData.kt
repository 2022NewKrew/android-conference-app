package com.example.data

import com.example.domain.entity.*
import com.google.gson.annotations.SerializedName

object MockData {

    val linkList = LinkList(
        file = listOf(),
        image = listOf(),
        webUrl = listOf(),
        video = listOf<Video>(),
        pcThumbnail = listOf(),
        moThumbnail = listOf(),
        talkThumbnail = listOf(),
        speakerProfile = listOf<SpeakerProfile>(),
        pcMainImage = listOf<PcMainImage>(),
        moMainImage = listOf<MoMainImage>(),
        pcImage = listOf<PcImage>(),
        moImage = listOf<MoImage>(),
        shareImage = listOf<ShareImage>(),
        pcSpotlight = listOf<PcSpotlight>(),
        moSpotlight = listOf<MoSpotlight>()
    )
    val relationList =
        RelationList(
            classification = listOf("ESG", "소셜 임펙트"),
            techClassification = listOf(),
            mainExposureDay = listOf("1Day")

        )
    val contentsSpeakerList = listOf(
        ContentsSpeakerList(
            idx = 1261,
            contentsIdx = 15, nameKo = "김혜일",
            nameEn = "haeppa",
            company = "링키지랩",
            occupation = "접근성팀"
        )
    )
    val dataList = listOf(
        Data(
            idx = 1,
            createdUserIdx = 2,
            createdDateTime = "2021-10-28 19:36:30",
            lastModifiedUserIdx = 1,
            lastModifiedDateTime = "2021-10-28 19:36:30",
            categoryIdx = 2,
            title = "모두를 위한 서비스, 카카오가 만들어가는 세상",
            content = "나에게 직관적이고 편리한 다양한 카카오의 서비스들, 과연 다른 모든 사람들도 편리하게 이용할 수 있을까?",
            contentTag = "#접근성 #보편성 #다양성 #a11y #accessibility #장애인 #책임 #UX/UI",
            spotlightYn = "Y",
            field = "서비",
            sessionType = "C,D type",
            commentYn = "Y",
            company = "카카오",
            reservationDate = 20211116,
            reservationTime = 1000,
            linkList = linkList,
            relationList = relationList,
            contentsSpeakerList = contentsSpeakerList,
            favoriteYn = "N",
            newCountentsYn = "N",
            updateCountentsYn = "N",
            companyName = "카카오",
            speakerName = "haeppa김혜일",
            speakerLoginYn = " N",
            reservationUTC = 1636992000000,
            reservationYn = "Y",
            videoYn = "Y"
        ),
        Data(
            idx = 2,
            createdUserIdx = 3,
            createdDateTime = "2021-10-28 16:46:38",
            lastModifiedUserIdx = 4,
            lastModifiedDateTime = "2021-10-28 16:46:38",
            categoryIdx = 6,
            title = "카카오임팩트 펠로우십을 통해 본  사회 혁신가의 성장 요인",
            content = "카카오임팩트 펠로우십은 사회 혁신가의 성장에 효과적일까요?",
            contentTag = "#카카오임팩트 #카카오임팩트펠로우십 #사회혁신가 #소셜이노베이터 #CSR",
            spotlightYn = "Y",
            field = "서비스",
            sessionType = "C,D type",
            commentYn = "Y",
            company = "카카오",
            reservationDate = 20211116,
            reservationTime = 1000,
            linkList = linkList,
            relationList = relationList,
            contentsSpeakerList = contentsSpeakerList,
            favoriteYn = "N",
            newCountentsYn = "N",
            updateCountentsYn = "N",
            companyName = "카카오",
            speakerName = "ive김대원",
            speakerLoginYn = " N",
            reservationUTC = 1636992000000,
            reservationYn = "Y",
            videoYn = "Y"
        ),
        Data(
            idx = 3,
            createdUserIdx = 2,
            createdDateTime = "2021-10-28 16:47:02",
            lastModifiedUserIdx = 16,
            lastModifiedDateTime = "2021-11-08 10:57:07",
            categoryIdx = 6,
            title = "카카오워크가 그리는 일하는 방식의 미래:  카카오워크 안에서 이뤄지는 업무의 완결",
            content = "올해 1주년을 맞이한 카카오워크는 카카오의 메신저 노하우와 AI 기술을 결합해 만들어진 올인원 종합 업무 플랫폼입니다.",
            contentTag = "#카카오엔터프라이즈 #카카오워크 #커뮤니케이션 #종합업무플랫폼 #협업툴 #디지털워크플레이스",
            spotlightYn = "Y",
            field = "서비스",
            sessionType = "C,D type",
            commentYn = "Y",
            company = "카카오엔터프라이즈",
            reservationDate = 20211116,
            reservationTime = 1000,
            linkList = linkList,
            relationList = relationList,
            contentsSpeakerList = contentsSpeakerList,
            favoriteYn = "N",
            newCountentsYn = "N",
            updateCountentsYn = "N",
            companyName = "카카오엔터프라이즈",
            speakerName = "jean임지희",
            speakerLoginYn = " N",
            reservationUTC = 1636992000000,
            reservationYn = "Y",
            videoYn = "Y"
        ),
    )

    val conference = Conference(
        success = true,
        data = dataList,
        code = 0,
        count = 3,
        errorMessage = ""
    )
}