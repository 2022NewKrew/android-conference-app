package com.survivalcoding.ifkakao.di

import com.survivalcoding.ifkakao.data.datasource.SessionService
import com.survivalcoding.ifkakao.data.datasource.SessionURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder().readTimeout(9, TimeUnit.SECONDS)
            .connectTimeout(9, TimeUnit.SECONDS).addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideConferenceRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(SessionURL.BASE_URL).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideConferenceService(retrofit: Retrofit): SessionService =
        retrofit.create(SessionService::class.java)
}