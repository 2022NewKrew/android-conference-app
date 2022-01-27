package com.survivalcoding.ifkakao

import android.app.Application
import com.survivalcoding.ifkakao.data.datasource.IkContentsDataSource
import com.survivalcoding.ifkakao.data.datasource.RetrofitClient
import com.survivalcoding.ifkakao.data.datasource.service.IkContentsService
import com.survivalcoding.ifkakao.data.repository.IkContentsRepositoryImpl
import com.survivalcoding.ifkakao.presentation.FragmentInformation
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.*

class App : Application() {
    private val service = RetrofitClient.getClient().create(IkContentsService::class.java)
    private val repository = IkContentsRepositoryImpl(IkContentsDataSource(service))
    val allSessions by lazy { runBlocking { repository.getSessions() } }
    val fragmentStack = Stack<FragmentInformation>()
}