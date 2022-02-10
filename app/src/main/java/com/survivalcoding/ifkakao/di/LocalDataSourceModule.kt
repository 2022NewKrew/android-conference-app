package com.survivalcoding.ifkakao.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {
//    @Binds
//    fun bindSessionDataSource(sessionLocalDataSource: SessionLocalDataSource): SessionDataSource

//    @Binds
//    fun bindLikeDataSource(likeRoomDataSource: LikeRoomDataSource): LikeDataSource
}