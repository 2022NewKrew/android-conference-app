package com.survivalcoding.ifkakao.di

import com.survivalcoding.ifkakao.data.datasource.SessionDataSource
import com.survivalcoding.ifkakao.data.datasource.SessionDataSourceImpl
import com.survivalcoding.ifkakao.data.datasource.SessionService
import com.survivalcoding.ifkakao.data.repository.SessionRepositoryImpl
import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideConferenceDataSource(sessionService: SessionService): SessionDataSource =
        SessionDataSourceImpl(sessionService)

    @Provides
    @Singleton
    fun provideSessionRepository(sessionDataSource: SessionDataSource): SessionRepository =
        SessionRepositoryImpl(sessionDataSource)
}