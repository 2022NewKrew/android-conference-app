package com.survivalcoding.ifkakao.domain.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize


@Parcelize
data class Session(
    val idx: Int,
    val categoryIdx: Int?,
    val commentYn: String?,
    val company: String?,
    val companyName: String?,
    val content: String?,
    val contentTag: String?,
    val contentsSpeakerList: List<ContentsSpeaker>?,
    val createdDateTime: String?,
    val createdUserIdx: Int?,
    val favoriteYn: String?,
    val `field`: String,
    val lastModifiedDateTime: String?,
    val lastModifiedUserIdx: Int?,
    val linkList: LinkList?,
    val newCountentsYn: String?,
    val qnaEndTime: String?,
    val qnaStartDay: String?,
    val qnaStartTime: String?,
    val relationList: RelationList,
    val reservationDate: String?,
    val reservationTime: String?,
    val reservationUTC: Long?,
    val reservationYn: String?,
    val sessionType: String?,
    val speakerLoginYn: String?,
    val speakerName: String?,
    val spotlightYn: String?,
    val title: String?,
    val updateCountentsYn: String?,
    val videoYn: String?
) : Parcelable