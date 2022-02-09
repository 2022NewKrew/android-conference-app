package com.survivalcoding.ifkakao.data.repository

import com.survivalcoding.ifkakao.data.datasource.IfKakaoService
import com.survivalcoding.ifkakao.domain.model.Content
import com.survivalcoding.ifkakao.domain.repository.ContentRepository
import java.lang.IllegalStateException
import javax.inject.Singleton

class RemoteContentRepository(private val ifKakaoService: IfKakaoService): ContentRepository {

    private var content: Content? = null

    override suspend fun getContent(): Content {
        if (content == null) {
            content = ifKakaoService.getContent()
        }

        return content!!
    }
}