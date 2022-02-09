package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity( primaryKeys = ["userName","idx"] )
data class LikeEntity(
   val userName: String,
   val idx: Int
)