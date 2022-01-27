package com.survivalcoding.ifkakao.di

import com.survivalcoding.ifkakao.data.datasource.like.LikeDataSource
import com.survivalcoding.ifkakao.data.datasource.like.LikeInMemoryDataSource
import com.survivalcoding.ifkakao.data.datasource.session.SessionDataSource
import com.survivalcoding.ifkakao.data.datasource.session.local.SessionLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun bindSessionDataSource(sessionLocalDataSource: SessionLocalDataSource): SessionDataSource

    @Binds
    fun bindLikeDataSource(likeInMemoryDataSource: LikeInMemoryDataSource): LikeDataSource
}