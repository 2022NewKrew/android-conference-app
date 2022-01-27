package com.survivalcoding.ifkakao.module

import com.example.data.network.IfKakaoService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class IfKakaoServiceModule {

    @Provides
    fun provideService(): IfKakaoService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://raw.githubusercontent.com")
        .build().create(IfKakaoService::class.java)

    @Provides
    fun providerGson(): Gson = Gson()

}