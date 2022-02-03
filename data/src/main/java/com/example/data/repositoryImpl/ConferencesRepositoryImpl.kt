package com.example.data.repositoryImpl

import com.example.data.network.ConferenceDataStore
import com.example.domain.entity.Conference
import com.example.domain.entity.ContentState
import com.example.domain.entity.Data
import com.example.domain.entity.OrderState
import com.example.domain.repository.ConferencesRepository
import javax.inject.Inject

class ConferencesRepositoryImpl @Inject constructor(private val conferenceDataStore: ConferenceDataStore) :
    ConferencesRepository {

    override suspend fun getConferences(): Conference? = conferenceDataStore.getConferences()

    override suspend fun getAllSessions(): List<Data>? = getConferences()?.data

    override suspend fun getSpotLightedSessions(): List<Data>? = getConferences()?.data?.filter {
        it.spotlightYn == "Y"
    }

    //todo like data
    override suspend fun getLikedSessions(): List<Data>? = getConferences()?.data

    override suspend fun getSessionsWithKeyWords(vararg keywords: String): List<Data>? =
        getConferences()?.data?.filter {
            it.relationList?.techClassification?.any { keyword -> keyword in keywords } ?: false
        }

    override suspend fun getSessionsWithField(field: String): List<Data>? =
        getConferences()?.data?.filter {
            it.field == field
        }

    override suspend fun getSortedDateSessions(
        date: Int,
        contentState: ContentState, orderState: OrderState
    ): List<Data>? {
        val result = getConferences()?.data?.filter {
            if (date == 20211118) true
            else it.reservationDate == date
        }
        if (result != null) {
            return when (orderState) {
                OrderState.asc -> {
                    if (contentState == ContentState.title) result.sortedBy { it.title }
                    else if (contentState == ContentState.company) result.sortedBy { it.company }
                    else result.sortedBy { it.categoryIdx }

                }
                OrderState.desc -> {
                    if (contentState == ContentState.title) result.sortedByDescending { it.title }
                    else if (contentState == ContentState.company) result.sortedByDescending { it.company }
                    else result.sortedByDescending { it.categoryIdx }
                }
            }
        } else {
            return null
        }
    }


}