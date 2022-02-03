package com.survivalcoding.ifkakao.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity
data class IkSessionLocalData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val comments: List<IkComment> = listOf(),
    val date: String = SimpleDateFormat("yyyy.MM.dd. hh:mm", Locale.getDefault()).format(Date()),
    val isLiked: Boolean = false,
)