package com.survivalcoding.ifkakao

import android.app.Application
import com.survivalcoding.ifkakao.data.datasource.local.MockLocalDataSource
import com.survivalcoding.ifkakao.data.datasource.remote.MockRemoteDataSource
import com.survivalcoding.ifkakao.data.repository.SessionRepositoryImpl
import com.survivalcoding.ifkakao.domain.repository.SessionRepository

class App : Application() {
    val sessionRepository: SessionRepository by lazy {
        SessionRepositoryImpl(
            MockRemoteDataSource(),
            //SessionRemoteDataSource(RetrofitClient.apiService),
            MockLocalDataSource(),
            /*
            SessionLocalDataSource(
                Room.databaseBuilder(
                    this,
                    IfKakaoDatabase::class.java,
                    "database"
                ).build().likeDao()
            )*/
        )
    }
}