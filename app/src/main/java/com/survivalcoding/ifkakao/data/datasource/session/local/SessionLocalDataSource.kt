package com.survivalcoding.ifkakao.data.datasource.session.local

import com.survivalcoding.ifkakao.data.datasource.session.SessionDataSource
import com.survivalcoding.ifkakao.domain.model.*

class SessionLocalDataSource : SessionDataSource {

    private var sessions: List<Session> = listOf(
        Session(
            idx = 20,
            createdUserIdx = 16,
            createdDateTime = "2021-10-28 15:38:53",
            lastModifiedUserIdx = 16,
            lastModifiedDateTime = "2021-11-10 10:39:47",
            categoryIdx = 6,
            title = "이용자의 소중한 일상을 지키는 카카오톡",
            content = "이용자의 소중한 일상을 지키려는 카카오톡의 노력을 소개합니다.\n\n카카오톡의 대화와 사진, 동영상뿐만 아니라 아이디와 비밀번호,\n대용량 파일도 안전하게 보관하는 '톡서랍' 업그레이드,\n디지털 세상에서 내가 나임을 증명할 수 있는 '신분증',\n환경 보호와 개인 정보 보호 두 마리 토끼를 잡는 '톡명함',\n그리고 '오픈채팅'에서의 이용자 보호 조치들에 대한 이야기를 나눕니다.",
            contentTag = "#카카오톡 #톡서랍업그레이드 #신분증 #톡명함 #오픈채팅이용자보호",
            spotlightYn = "Y",
            field = "서비스",
            sessionType = "A Type",
            commentYn = "Y",
            company = "카카오",
            reservationDate = "20211116",
            reservationTime = "1000",
            linkList = LinkList(
                file = emptyList(),
                video = listOf(
                    File(
                        idx = 3683,
                        contentsIdx = 20,
                        type = "VIDEO",
                        fileSize = 0,
                        url = "https://tv.kakao.com/embed/player/cliplink/423791694",
                        description = "09:41",
                        mainYn = "N"
                    )
                ),
                speakerProfile = listOf(
                    File(
                        idx = 3682,
                        contentsIdx = 20,
                        type = " SPEAKER_PROFILE ",
                        fileSize = 109555,
                        url = "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635403129640",
                        description = "If Kakao_CPO_1022.png",
                        mainYn = "N"
                    )
                ),
                pcImage = listOf(
                    File(
                        idx = 3679,
                        contentsIdx = 20,
                        type = "PC_IMAGE",
                        fileSize = 68188,
                        url = "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635403330102",
                        description = "***1027_A세션_썸네일_2.png",
                        mainYn = "Y"
                    )
                )
            ),
            relationList = RelationList(
                classification = listOf("플랫폼", "디지털자산"),
                techClassification = emptyList(),
                mainExposureDay = listOf("1Day")
            ),
            contentsSpeakerList = listOf(
                ContentsSpeaker(
                    idx = 1273,
                    contentsIdx = 20,
                    nameKo = "김택수",
                    nameEn = "felix",
                    company = "카카오",
                    occupation = "CPO",
                    loginEmail = "",
                    channelLink = ""
                )
            ),
            favoriteYn = "N",
            newCountentsYn = "N",
            updateCountentsYn = "N",
            companyName = "카카오",
            speakerName = "felix김택수",
            speakerLoginYn = "N",
            reservationUTC = 1636992000000,
            reservationYn = "Y",
            videoYn = "Y"
        )
    )

    override suspend fun getSessionAll(): List<Session> = sessions

    override suspend fun getSessionById(id: Int): Session {
        TODO("Not yet implemented")
    }

    override suspend fun sortByTitleAsc(): List<Session> =
        sessions.sortedBy { it.title }

    override suspend fun sortByTitleDesc(): List<Session> =
        sessions.sortedByDescending { it.title }

    override suspend fun sortByCompanyAsc(): List<Session> =
        sessions.sortedBy { it.companyName }

    override suspend fun sortByCompanyDesc(): List<Session> =
        sessions.sortedByDescending { it.companyName }

    override suspend fun sortByCategoryAsc(): List<Session> =
        sessions.sortedBy { it.categoryIdx }

    override suspend fun sortByCategoryDesc(): List<Session> =
        sessions.sortedByDescending { it.categoryIdx }

}