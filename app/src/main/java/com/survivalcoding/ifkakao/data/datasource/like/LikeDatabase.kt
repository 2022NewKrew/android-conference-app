package com.survivalcoding.ifkakao.data.datasource.like

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.util.TypeConverter

@Database(entities = [Session::class], version = 1)
@TypeConverters(value = [TypeConverter::class])
abstract class LikeDatabase : RoomDatabase() {
    abstract fun likeDao(): LikeDao
}