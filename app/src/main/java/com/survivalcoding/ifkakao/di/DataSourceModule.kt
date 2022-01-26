package com.survivalcoding.ifkakao.di

import com.survivalcoding.ifkakao.data.datasource.like.LikeDataSource
import com.survivalcoding.ifkakao.data.datasource.like.LikeInMemoryDataSource
import com.survivalcoding.ifkakao.data.datasource.session.SessionDataSource
import com.survivalcoding.ifkakao.data.datasource.session.local.SessionLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Provides
    fun provideSessionDataSource(): SessionDataSource {
        return SessionLocalDataSource()
    }

    @Provides
    fun provideLikeDataSource(): LikeDataSource {
        return LikeInMemoryDataSource()
    }
}