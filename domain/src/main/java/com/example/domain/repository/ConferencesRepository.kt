package com.example.domain.repository

import com.example.domain.entity.Conference
import kotlinx.coroutines.flow.Flow

interface ConferencesRepository {
    suspend fun getConferences(): Conference
}