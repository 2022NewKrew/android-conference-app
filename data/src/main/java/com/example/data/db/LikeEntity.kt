package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class LikeEntity(
    @PrimaryKey
    val userName: String,
    val idx: Int
)