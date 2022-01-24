package com.survivalcoding.ifkakao

import android.app.Application
import com.survivalcoding.ifkakao.data.datasource.IkContentsDataSource
import com.survivalcoding.ifkakao.data.datasource.RetrofitClient
import com.survivalcoding.ifkakao.data.datasource.service.IkContentsService
import com.survivalcoding.ifkakao.data.repository.IkContentsRepositoryImpl

class App : Application() {
    private val service = RetrofitClient.getClient().create(IkContentsService::class.java)
    val repository = IkContentsRepositoryImpl(IkContentsDataSource(service))
}