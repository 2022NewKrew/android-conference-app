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

    private fun checkContentIsNotNull() {
        if(content == null) {
            throw IllegalStateException("content is null. Call getContent() before calling this method.")
        }
    }

    override fun getFilterFieldItems(): List<String> {
        checkContentIsNotNull()

        val items = mutableListOf<String>()
        content!!.data.forEach {
            items += it.field
        }

        return items.toSet().toList()
    }

    override fun getFilterClassificationItems(): List<String> {
        checkContentIsNotNull()

        val items = mutableListOf<String>()
        content!!.data.forEach {
            items += it.relationList.CLASSIFICATION
        }

        return items.toSet().toList()
    }

    override fun getFilterTechnicalClassificationItems(): List<String> {
        checkContentIsNotNull()

        val items = mutableListOf<String>()
        content!!.data.forEach {
            items += it.relationList.TECH_CLASSIFICATION
        }

        return items.toSet().toList()
    }

    override fun getFilterCompanyItems(): List<String> {
        checkContentIsNotNull()

        val items = mutableListOf<String>()
        content!!.data.forEach {
            items += it.companyName
        }

        return items.toSet().toList()
    }
}