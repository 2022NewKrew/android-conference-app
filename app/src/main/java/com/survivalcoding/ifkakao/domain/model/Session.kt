package com.survivalcoding.ifkakao.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Session(
    val idx: Int = 0,
    val categoryIdx: Int? = null,
    val commentYn: String? = null,
    val company: String? = null,
    val companyName: String? = null,
    val content: String? = null,
    val contentTag: String? = null,
    val contentsSpeakerList: List<ContentsSpeaker>? = null,
    val createdDateTime: String? = null,
    val createdUserIdx: Int? = null,
    val favoriteYn: String? = null,
    val `field`: String = "",
    val lastModifiedDateTime: String? = null,
    val lastModifiedUserIdx: Int? = null,
    val linkList: LinkList? = null,
    val newCountentsYn: String? = null,
    val qnaEndTime: String? = null,
    val qnaStartDay: String? = null,
    val qnaStartTime: String? = null,
    val relationList: RelationList = RelationList(null, emptyList(), null),
    val reservationDate: String? = null,
    val reservationTime: String? = null,
    val reservationUTC: Long? = null,
    val reservationYn: String? = null,
    val sessionType: String? = null,
    val speakerLoginYn: String? = null,
    val speakerName: String? = null,
    val spotlightYn: String? = null,
    val title: String? = null,
    val updateCountentsYn: String? = null,
    val videoYn: String? = null
) : Parcelable