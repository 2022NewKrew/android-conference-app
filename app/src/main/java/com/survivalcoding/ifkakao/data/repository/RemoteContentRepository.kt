package com.survivalcoding.ifkakao.data.repository

import com.survivalcoding.ifkakao.data.datasource.IfKakaoService
import com.survivalcoding.ifkakao.domain.model.Content
import com.survivalcoding.ifkakao.domain.repository.ContentRepository

class RemoteContentRepository(private val ifKakaoService: IfKakaoService): ContentRepository {

    override suspend fun getContent(): Content {
        return ifKakaoService.getContent()
    }
}