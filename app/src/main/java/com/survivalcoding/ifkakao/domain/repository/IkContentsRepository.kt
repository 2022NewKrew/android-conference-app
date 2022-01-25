package com.survivalcoding.ifkakao.domain.repository

import com.survivalcoding.ifkakao.domain.model.IkSessionData

interface IkContentsRepository {
    suspend fun getSessions(): List<IkSessionData>
}
