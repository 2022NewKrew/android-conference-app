package com.survivalcoding.ifkakao.di

import com.survivalcoding.ifkakao.data.datasource.SessionDataSource
import com.survivalcoding.ifkakao.data.datasource.SessionDataSourceImpl
import com.survivalcoding.ifkakao.data.datasource.SessionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideConferenceDataSource(sessionService: SessionService): SessionDataSource =
        SessionDataSourceImpl(sessionService)
}