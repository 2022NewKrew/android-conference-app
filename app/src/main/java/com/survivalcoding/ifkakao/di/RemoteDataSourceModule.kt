package com.survivalcoding.ifkakao.di

import android.content.Context
import androidx.room.Room
import com.survivalcoding.ifkakao.data.datasource.like.LikeDataSource
import com.survivalcoding.ifkakao.data.datasource.like.LikeDatabase
import com.survivalcoding.ifkakao.data.datasource.like.LikeRoomDataSource
import com.survivalcoding.ifkakao.data.datasource.session.SessionDataSource
import com.survivalcoding.ifkakao.data.datasource.session.remote.RetrofitClient
import com.survivalcoding.ifkakao.data.datasource.session.remote.SessionRemoteDataSource
import com.survivalcoding.ifkakao.data.datasource.session.remote.service.SessionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {
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

    @Provides
    @Singleton
    fun provideSessionDataSource(): SessionDataSource {
        return SessionRemoteDataSource(
            RetrofitClient.getClient().create(SessionService::class.java)
        )
    }
}