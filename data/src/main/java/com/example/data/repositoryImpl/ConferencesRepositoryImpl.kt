package com.example.data.repositoryImpl

import com.example.data.network.ConferenceDataStore
import com.example.data.network.IfKakaoService
import com.example.domain.entity.Conference
import com.example.domain.entity.Data
import com.example.domain.repository.ConferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConferencesRepositoryImpl @Inject constructor(private val conferenceDataStore: ConferenceDataStore) :
    ConferencesRepository {

    override suspend fun getConferences(): Conference = conferenceDataStore.getConferences()

    override suspend fun getAllSessions(): List<Data> = getConferences().data

    override suspend fun getSpotLightedSessions(): List<Data> = getConferences().data.filter {
        it.spotlightYn == "Y"
    }

    override suspend fun getSessionsFromDate(date: Int): List<Data> =
        getConferences().data.filter {
            if (date == 20211118) true
            else it.reservationDate == date
        }

    //todo like data
    override suspend fun getLikedSessions(): List<Data> = getConferences().data

    override suspend fun getSessionsWithKeyWords(vararg keywords: String): List<Data> =
        getConferences().data.filter {
            it.relationList.techClassification.any { keyword -> keyword in keywords }
        }


}