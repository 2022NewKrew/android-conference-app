package com.survivalcoding.ifkakao.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Session(
    val idx: Int = 0,
    val company: String? = null,
    val companyName: String? = null,
    val content: String? = null,
    val contentTag: String? = null,
    val contentsSpeakerList: List<ContentsSpeaker>? = null,
    val `field`: String = "",
    val linkList: LinkList? = null,
    val relationList: RelationList = RelationList(emptyList(), emptyList(), emptyList()),
    val speakerName: String? = null,
    val spotlightYn: String? = null,
    val title: String? = null,
) : Parcelable