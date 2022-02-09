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


    override suspend fun getSessionsWithKeyWords(keyWords: List<String>): List<Data>? {
        val result = getAllSessions()?.filter { item ->
            checkKeyWords(item, keyWords)
        }
        return result
    }

    //field
    //contentspeaker company
    //relationlist tech classification (기술)
    //relationlist classification (서비스.비즈니스)
    override suspend fun getSessionsWithKeyWordsAndDate(
        date: Int,
        keyWords: List<String>
    ): List<Data>? {
        val result = getSessionsWithDate(date)?.filter { item ->
            checkKeyWords(item, keyWords)
        }
        return result
    }

    private fun checkKeyWords(item: Data, keyWords: List<String>): Boolean {
        item.field?.let {
            if (it in keyWords) return true
        }
        item.relationList?.classification?.let {
            if (it.any { keyWord -> keyWord in keyWords }) return true
        }

        item.relationList?.techClassification?.let {
            if (it.any { keyWord -> keyWord in keyWords }) return true
        }
        item.contentsSpeakerList?.get(0)?.let {
            if (it.company in keyWords) return true
        }

        return false
    }

    override suspend fun getSessionsWithField(field: String): List<Data>? =
        getConferences()?.data?.filter {
            it.field == field
        }

    override suspend fun getSessionsWithDate(date: Int): List<Data>? =
        getConferences()?.data?.filter {
            if (date == 20211118) true
            else it.reservationDate == date
        }

    override suspend fun getSortedDateSessions(
        date: Int,
        contentState: ContentState, orderState: OrderState
    ): List<Data>? {
        val result = getSessionsWithDate(date)
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