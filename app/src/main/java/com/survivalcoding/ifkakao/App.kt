package com.survivalcoding.ifkakao

import android.app.Application
import com.survivalcoding.ifkakao.data.datasource.RetrofitClient
import com.survivalcoding.ifkakao.data.datasource.service.IkContentsService

class App : Application() {
    private val service = RetrofitClient.getClient().create(IkContentsService::class.java)
}