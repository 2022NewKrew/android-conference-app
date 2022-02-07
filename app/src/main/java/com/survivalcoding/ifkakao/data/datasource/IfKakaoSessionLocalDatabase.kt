package com.survivalcoding.ifkakao.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.survivalcoding.ifkakao.domain.model.CommentListTypeConverter
import com.survivalcoding.ifkakao.domain.model.IkSessionLocalData

@Database(entities = [IkSessionLocalData::class], version = 1, exportSchema = false)
@TypeConverters(
    value = [
        CommentListTypeConverter::class
    ]
)
abstract class IfKakaoSessionLocalDatabase : RoomDatabase() {
    abstract fun sessionDao(): IfKakaoSessionDao
}