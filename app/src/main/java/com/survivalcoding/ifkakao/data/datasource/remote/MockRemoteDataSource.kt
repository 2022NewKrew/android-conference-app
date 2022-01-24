package com.survivalcoding.ifkakao.data.datasource.remote

import com.survivalcoding.ifkakao.data.datasource.MockData
import com.survivalcoding.ifkakao.domain.repository.SessionRemoteRepository

class MockRemoteDataSource : SessionRemoteRepository {
    override suspend fun getIfKakaoData() = MockData.getIfKakaoData()
}