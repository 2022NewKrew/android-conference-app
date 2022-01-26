package com.survivalcoding.ifkakao.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LinkList(
//    val FILE: List<FILE>,
//    val IMAGE: List<Any>,
//    val MO_IMAGE: List<MOIMAGE>,
//    val MO_MAIN_IMAGE: List<MOMAINIMAGE>,
//    val MO_SPOTLIGHT: List<MOSPOTLIGHT>,
//    val MO_THUMBNAIL: List<Any>,
    val PC_IMAGE: List<PCIMAGE>,
//    val PC_MAIN_IMAGE: List<PCMAINIMAGE>,
//    val PC_SPOTLIGHT: List<PCSPOTLIGHT>,
//    val PC_THUMBNAIL: List<Any>,
//    val SHARE_IMAGE: List<SHAREIMAGE>,
    val SPEAKER_PROFILE: List<SPEAKERPROFILE>,
//    val TALK_THUMBNAIL: List<Any>,
    val VIDEO: List<VIDEO>,
//    val WEB_URL: List<Any>
): Parcelable