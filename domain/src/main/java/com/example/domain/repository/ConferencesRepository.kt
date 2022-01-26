package com.example.domain.repository

import com.example.domain.entity.Conference
import com.example.domain.entity.Data
import kotlinx.coroutines.flow.Flow

interface ConferencesRepository {
    suspend fun getConferences(): Conference

    suspend fun getAllSessions(): List<Data>

    suspend fun getSpotLightedSessions(): List<Data>

    suspend fun getSessionsFromDate(date: Int): List<Data>

    suspend fun getLikedSessions(): List<Data>

    suspend fun getSessionsWithKeyWords(vararg keywords: String): List<Data>

}