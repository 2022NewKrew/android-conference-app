package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.IkSessionLocalData
import com.survivalcoding.ifkakao.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class GetLocalSessionDataUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    operator fun invoke(id: Int): Flow<IkSessionLocalData> {
        runBlocking {
            if (localRepository.getSessionLocalDataById(id).firstOrNull() == null) {
                localRepository.insertSessionLocalData(IkSessionLocalData(id))
            }
        }
        return localRepository.getSessionLocalDataById(id)
    }
}