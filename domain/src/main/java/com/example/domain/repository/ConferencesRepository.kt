package com.example.domain.repository

import com.example.domain.entity.Conference
import com.example.domain.entity.ContentState
import com.example.domain.entity.Data
import com.example.domain.entity.OrderState
import kotlinx.coroutines.flow.Flow

interface ConferencesRepository {
    suspend fun getConferences(): Conference?

    suspend fun getAllSessions(): List<Data>?

    suspend fun getSpotLightedSessions(): List<Data>?

    suspend fun getLikedSessions(): List<Data>?

    suspend fun getSessionsWithKeyWords(keyWords: List<String>): List<Data>?


    suspend fun getSessionsWithKeyWordsAndDate(date: Int, keyWords: List<String>): List<Data>?

    suspend fun getSessionsWithField(field: String): List<Data>?

    suspend fun getSessionsWithDate(date: Int): List<Data>?

    suspend fun getSortedDateWithKeyWordsSessions(
        date: Int,
        keyWords: List<String>,
        contentState: ContentState,
        orderState: OrderState
    ): List<Data>?

}