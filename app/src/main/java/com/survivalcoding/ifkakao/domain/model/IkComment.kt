package com.survivalcoding.ifkakao.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class IkComment(
    val id: Int,
    val name: String,
    val date: String = SimpleDateFormat("yyyy.MM.dd. HH:mm", Locale.getDefault()).format(Date()),
    val content: String,
) : Parcelable