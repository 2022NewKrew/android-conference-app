package com.survivalcoding.ifkakao.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IkContentDTO(
    val code: Int,
    val count: Int,
    val data: List<IkDataDTO>,
    val errorMessage: String?,
    val success: Boolean
) : Parcelable

@Parcelize
data class IkDataDTO(
    val categoryIdx: Int,
    val commentYn: String,
    val company: String,
    val companyName: String,
    val content: String,
    val contentTag: String,
    val contentsSpeakerList: List<IkContentsSpeakerDTO>,
    val createdDateTime: String,
    val createdUserIdx: Int,
    val favoriteYn: String,
    val `field`: String,
    val idx: Int,
    val lastModifiedDateTime: String,
    val lastModifiedUserIdx: Int,
    val linkList: IkLinkDTO,
    val newCountentsYn: String,
    val qnaEndTime: String,
    val qnaStartDay: String,
    val qnaStartTime: String,
    val relationList: IkRelationDTO,
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
    val videoYn: String
) : Parcelable

@Parcelize
data class IkContentsSpeakerDTO(
    val channelLink: String,
    val company: String,
    val contentsIdx: Int,
    val idx: Int,
    val loginEmail: String,
    val nameEn: String,
    val nameKo: String,
    val occupation: String
) : Parcelable

@Parcelize
data class IkLinkDTO(
    val FILE: List<IkFileDTO>,
    val MO_IMAGE: List<IkMobileImageDTO>,
    val MO_MAIN_IMAGE: List<IkMobileMainImageDTO>,
    val MO_SPOTLIGHT: List<IkMobileSpotlightDTO>,
    val SPEAKER_PROFILE: List<IkSpeakerProfileDTO>,
    val VIDEO: List<IkVideoDTO>,
) : Parcelable

@Parcelize
data class IkFileDTO(
    val contentsIdx: Int,
    val description: String,
    val fileSize: Int,
    val idx: Int,
    val mainYn: String,
    val type: String,
    val url: String
) : Parcelable

@Parcelize
data class IkMobileImageDTO(
    val contentsIdx: Int,
    val description: String,
    val fileSize: Int,
    val idx: Int,
    val mainYn: String,
    val type: String,
    val url: String
) : Parcelable

@Parcelize
data class IkMobileMainImageDTO(
    val contentsIdx: Int,
    val description: String,
    val fileSize: Int,
    val idx: Int,
    val mainYn: String,
    val type: String,
    val url: String
) : Parcelable

@Parcelize
data class IkMobileSpotlightDTO(
    val contentsIdx: Int,
    val description: String,
    val fileSize: Int,
    val idx: Int,
    val mainYn: String,
    val type: String,
    val url: String
) : Parcelable

@Parcelize
data class IkSpeakerProfileDTO(
    val contentsIdx: Int,
    val description: String,
    val fileSize: Int,
    val idx: Int,
    val mainYn: String,
    val type: String,
    val url: String
) : Parcelable

@Parcelize
data class IkVideoDTO(
    val contentsIdx: Int,
    val description: String,
    val fileSize: Int,
    val idx: Int,
    val mainYn: String,
    val type: String,
    val url: String
) : Parcelable

@Parcelize
data class IkRelationDTO(
    val CLASSIFICATION: List<String>,
    val MAIN_EXPOSURE_DAY: List<String>,
    val TECH_CLASSIFICATION: List<String>
) : Parcelable