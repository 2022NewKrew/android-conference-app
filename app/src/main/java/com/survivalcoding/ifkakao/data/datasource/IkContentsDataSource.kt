package com.survivalcoding.ifkakao.data.datasource

import com.survivalcoding.ifkakao.data.datasource.service.IkContentsService
import com.survivalcoding.ifkakao.data.dto.IkContentDTO

class IkContentsDataSource(
    private val ikContentsService: IkContentsService
) {
    suspend fun getContents(): IkContentDTO {
        return ikContentsService.getContents()
    }
}