package com.survivalcoding.ifkakao.data.datasource

import com.survivalcoding.ifkakao.domain.model.IfKakaoData

object MockData {
    val data: IfKakaoData = IfKakaoData() // ToDo: mock 데이터 생성
    fun getIfKakaoData(): IfKakaoData = data
}