package com.survivalcoding.ifkakao.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.survivalcoding.ifkakao.domain.model.IkSessionLocalData

@Database(entities = [IkSessionLocalData::class], version = 1, exportSchema = false)
abstract class IfKakaoSessionLocalDatabase : RoomDatabase() {
    abstract fun sessionDao(): IfKakaoSessionDao
}