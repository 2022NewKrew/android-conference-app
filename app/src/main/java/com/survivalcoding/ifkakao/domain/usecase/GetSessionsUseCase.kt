package com.survivalcoding.ifkakao.domain.usecase

import android.util.Log
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.repository.SessionRemoteRepository

class GetSessionsUseCase(private val repository: SessionRemoteRepository) {
    suspend operator fun invoke(): List<Session> =
        try {
            repository.getIfKakaoData().data
        } catch (e: Exception) {
            Log.d("getSessionError", "Cannot access to internet")
            emptyList()
        }
}