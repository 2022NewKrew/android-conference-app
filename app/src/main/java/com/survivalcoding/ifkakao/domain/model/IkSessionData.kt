package com.survivalcoding.ifkakao.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IkSessionData(
    val isValid: Boolean = true,
    val idx: Int,
    val company: String,
    val title: String,
    val content: String,
    val contentTag: String,
    val sessionSpeakers: List<IkSessionSpeaker>,
    val field: String,
    val spotlightYn: String,
    val videoYn: String,
    val linkLists: IkLinkLists,
    val relationLists: IkRelationLists,
) : Parcelable