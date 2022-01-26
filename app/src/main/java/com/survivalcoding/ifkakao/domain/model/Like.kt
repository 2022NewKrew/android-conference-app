package com.survivalcoding.ifkakao.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Like(
    @PrimaryKey val idx: Int,
    val id: Int
)