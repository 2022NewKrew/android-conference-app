package com.survivalcoding.ifkakao.data.repository

import com.survivalcoding.ifkakao.data.datasource.SessionDataSourceImpl
import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(private val conferenceDataSourceImpl: SessionDataSourceImpl) :
    SessionRepository