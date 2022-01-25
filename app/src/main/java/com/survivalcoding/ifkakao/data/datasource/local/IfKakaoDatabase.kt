package com.survivalcoding.ifkakao.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.survivalcoding.ifkakao.domain.model.Like

@Database(entities = [Like::class], version = 1)
abstract class IfKakaoDatabase : RoomDatabase() {
    abstract fun likeDao(): LikeDao
}