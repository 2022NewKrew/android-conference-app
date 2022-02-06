package com.survivalcoding.ifkakao.domain.entity

import com.survivalcoding.ifkakao.domain.model.ContentsSpeaker
import com.survivalcoding.ifkakao.domain.model.LinkList
import com.survivalcoding.ifkakao.domain.model.RelationList

data class Session(
    val id: Int,
    val categoryIdx: Int,
    val commentYn: String,
    val company: String,
    val companyName: String,
    val content: String,
    val contentTag: String,
    val createdDateTime: String,
    val createdUserIdx: Int,
    val favoriteYn: String,
    val `field`: String,
    val idx: Int,
    val lastModifiedDateTime: String,
    val lastModifiedUserIdx: Int,
    val newCountentsYn: String,
    val qnaEndTime: String,
    val qnaStartDay: String,
    val qnaStartTime: String,
    val reservationDate: String,
    val reservationTime: String,
    val reservationUTC: Long,
    val reservationYn: String,
    val sessionType: String,
    val speakerLoginYn: String,
    val speakerName: String,
    val spotlightYn: String,
    val title: String,
    val updateCountentsYn: String,
    val videoYn: String,
    val thumbnailUrl: String
)

data class Speaker(
    val nameEn: String,
    val nameKo: String,
    val company: String,
    val occupation: String,
    val channelLink: String,
    val profileUrl: String
)

data class Classification(
    val name: String
)
