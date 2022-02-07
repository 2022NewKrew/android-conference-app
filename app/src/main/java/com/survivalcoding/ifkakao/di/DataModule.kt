package com.survivalcoding.ifkakao.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.survivalcoding.ifkakao.data.datasource.IfKakaoService
import com.survivalcoding.ifkakao.data.datasource.IfKakaoSessionDao
import com.survivalcoding.ifkakao.data.datasource.IfKakaoSessionLocalDatabase
import com.survivalcoding.ifkakao.data.repository.SessionLocalRepositoryImpl
import com.survivalcoding.ifkakao.data.repository.SessionRemoteRepositoryImpl
import com.survivalcoding.ifkakao.domain.model.CommentListTypeConverter
import com.survivalcoding.ifkakao.domain.model.IfKakaoContent
import com.survivalcoding.ifkakao.domain.repository.LocalRepository
import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import com.survivalcoding.ifkakao.presentation.util.FragmentType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        return Stack<FragmentInformation>().apply { push(FragmentInformation(fragmentType = FragmentType.HIGHLIGHT)) }
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideSessionDao(@ApplicationContext context: Context, gson: Gson): IfKakaoSessionDao {
        return Room.databaseBuilder(
            context,
            IfKakaoSessionLocalDatabase::class.java,
            "sessionLocalDB"
        ).addTypeConverter(CommentListTypeConverter(gson))
            .build().sessionDao()
    }

    @Provides
    @Singleton
    fun provideSessionLocalRepository(dao: IfKakaoSessionDao): LocalRepository {
        return SessionLocalRepositoryImpl(dao)
    }
}