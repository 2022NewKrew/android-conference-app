package com.survivalcoding.ifkakao.data.repository

import com.survivalcoding.ifkakao.data.datasource.ConferenceDataSourceImpl
import com.survivalcoding.ifkakao.domain.repository.ConferenceRepository
import javax.inject.Inject

class ConferenceRepositoryImpl @Inject constructor(private val conferenceDataSourceImpl: ConferenceDataSourceImpl) :
    ConferenceRepository