package com.survivalcoding.ifkakao.domain.repository

import com.survivalcoding.ifkakao.domain.model.Content

interface ContentRepository {

    suspend fun getContent(): Content
}