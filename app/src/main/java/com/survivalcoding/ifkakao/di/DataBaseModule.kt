package com.survivalcoding.ifkakao.di

import android.content.Context
import androidx.room.Room
import com.survivalcoding.ifkakao.data.datasource.like.LikeDataSource
import com.survivalcoding.ifkakao.data.datasource.like.LikeDatabase
import com.survivalcoding.ifkakao.data.datasource.like.LikeRoomDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideLikeDataSource(
        @ApplicationContext context: Context
    ): LikeDataSource {
        return LikeRoomDataSource(
            Room.databaseBuilder(
                context,
                LikeDatabase::class.java,
                "LikeDatabase"
            ).build().likeDao()
        )
    }
}