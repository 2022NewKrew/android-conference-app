package com.survivalcoding.ifkakao

import android.app.Application
import com.example.data.network.ConferenceDataStore
import com.example.data.network.IfKakaoService
import com.example.data.repositoryImpl.ConferencesRepositoryImpl
import com.example.domain.repository.ConferencesRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApp : Application() {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://raw.githubusercontent.com")
            .build()
    }

    val conferenceRepository: ConferencesRepository by lazy {
        ConferencesRepositoryImpl(
            ConferenceDataStore(retrofit.create(IfKakaoService::class.java))
        )
    }


}