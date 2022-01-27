package com.survivalcoding.ifkakao.di

import com.survivalcoding.ifkakao.data.datasource.IfKakaoService
import com.survivalcoding.ifkakao.data.repository.IfKakaoRemoteRepositoryImpl
import com.survivalcoding.ifkakao.domain.model.IfKakaoContent
import com.survivalcoding.ifkakao.domain.repository.IfKakaoRepository
import com.survivalcoding.ifkakao.presentation.FragmentInformation
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
object IkSessionRepositoryModule {

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
    fun provideIfKakaoRepository(ifKakaoService: IfKakaoService): IfKakaoRepository {
        return IfKakaoRemoteRepositoryImpl(ifKakaoService)
    }

    @Provides
    @Singleton
    fun provideIfKakaoContent(ifKakaoRepository: IfKakaoRepository): IfKakaoContent {
        var res: IfKakaoContent?
        runBlocking { res = ifKakaoRepository.getContent() }
        return res ?: IfKakaoContent(listOf(), false)
    }

    @Provides
    @Singleton
    fun provideFragmentStack(): Stack<FragmentInformation> {
        return Stack()
    }
}