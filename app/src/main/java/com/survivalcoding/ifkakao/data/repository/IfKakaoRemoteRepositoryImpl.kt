package com.survivalcoding.ifkakao.data.repository

import com.survivalcoding.ifkakao.data.datasource.IfKakaoService
import com.survivalcoding.ifkakao.data.dto.IkContentDTO
import com.survivalcoding.ifkakao.data.dto.toEntity
import com.survivalcoding.ifkakao.domain.model.IfKakaoContent
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.repository.IfKakaoRepository
import javax.inject.Inject

class IfKakaoRemoteRepositoryImpl(
    private val ifKakaoService: IfKakaoService,
) : IfKakaoRepository {
    override suspend fun getContent(): IfKakaoContent {
        return ifKakaoService.getContent().toEntity()
    }
}