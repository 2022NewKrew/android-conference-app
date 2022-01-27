package com.survivalcoding.ifkakao

import android.app.Application
import com.example.data.network.ConferenceDataStore
import com.example.data.network.IfKakaoService
import com.example.data.repositoryImpl.ConferencesRepositoryImpl
import com.example.domain.repository.ConferencesRepository
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@HiltAndroidApp
class MyApp : Application() {
}