package com.survivalcoding.ifkakao.data.datasource

import com.survivalcoding.ifkakao.data.vo.DataVo

interface ConferenceDataSource {
    suspend fun getData(): List<DataVo>?
    suspend fun getItem(idx: Int): DataVo?
}