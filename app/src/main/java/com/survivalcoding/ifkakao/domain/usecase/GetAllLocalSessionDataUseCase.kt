package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.IkSessionLocalData
import com.survivalcoding.ifkakao.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllLocalSessionDataUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    operator fun invoke(): Flow<List<IkSessionLocalData>> {
        return localRepository.getAllSessionLocalData()
    }
}