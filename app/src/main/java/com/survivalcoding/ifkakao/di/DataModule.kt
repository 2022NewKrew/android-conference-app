package com.survivalcoding.ifkakao.di

import com.survivalcoding.ifkakao.data.datasource.IfKakaoService
import com.survivalcoding.ifkakao.data.repository.SessionRemoteRepositoryImpl
import com.survivalcoding.ifkakao.domain.model.IfKakaoContent
import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideIfKakaoService(): IfKakaoService {
        return Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IfKakaoService::class.java)
    }

    @Provides
    @Singleton
    fun provideIfKakaoRepository(ifKakaoService: IfKakaoService): SessionRepository {
        return SessionRemoteRepositoryImpl(ifKakaoService)
    }

    @Provides
    @Singleton
    fun provideIfKakaoContent(sessionRepository: SessionRepository): IfKakaoContent {
        var res: IfKakaoContent?
        runBlocking { res = sessionRepository.getContent() }
        return res ?: IfKakaoContent(listOf(), false)
    }

    @Provides
    @Singleton
    fun provideFragmentStack(): Stack<FragmentInformation> {
        return Stack()
    }
}