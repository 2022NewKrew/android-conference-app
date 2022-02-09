package com.survivalcoding.ifkakao.module

import android.content.Context
import androidx.room.Room
import com.example.data.db.LikeDao
import com.example.data.db.LikeDatabase
import com.example.domain.entity.Like
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LikeModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LikeDatabase =
        Room.databaseBuilder(context, LikeDatabase::class.java, LikeDatabase.DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideDao(db: LikeDatabase): LikeDao = db.likeDao()
}