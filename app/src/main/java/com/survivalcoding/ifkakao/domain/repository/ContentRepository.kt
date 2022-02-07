package com.survivalcoding.ifkakao.domain.repository

import com.survivalcoding.ifkakao.domain.model.Content

interface ContentRepository {

    suspend fun getContent(): Content

    fun getFilterFieldItems(): List<String>

    fun getFilterClassificationItems(): List<String>

    fun getFilterTechnicalClassificationItems(): List<String>

    fun getFilterCompanyItems(): List<String>
}