package com.survivalcoding.ifkakao.di

import com.survivalcoding.ifkakao.data.datasource.IfKakaoService
import com.survivalcoding.ifkakao.data.repository.RemoteContentRepository
import com.survivalcoding.ifkakao.domain.repository.ContentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object IfKakaoModule {

    @Provides
    fun provideIfKakaoService(): IfKakaoService {
        return Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(MoshiConverterFactory.create()).build()
            .create(IfKakaoService::class.java)
    }

    @Provides
    fun provideContentRepository(ifKakaoService: IfKakaoService): ContentRepository {
        return RemoteContentRepository(ifKakaoService)
    }
}