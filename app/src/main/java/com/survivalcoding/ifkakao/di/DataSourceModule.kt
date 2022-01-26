package com.survivalcoding.ifkakao.di

import com.survivalcoding.ifkakao.data.datasource.ConferenceDataSource
import com.survivalcoding.ifkakao.data.datasource.ConferenceDataSourceImpl
import com.survivalcoding.ifkakao.data.datasource.ConferenceService
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
    fun provideConferenceDataSource(conferenceService: ConferenceService): ConferenceDataSource =
        ConferenceDataSourceImpl(conferenceService)
}