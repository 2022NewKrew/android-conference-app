package com.survivalcoding.ifkakao.data.datasource

import com.survivalcoding.ifkakao.domain.model.ApiDto
import com.survivalcoding.ifkakao.domain.repository.NetworkResult
import retrofit2.Response
import javax.inject.Inject

class ConferenceDataSource @Inject constructor(private val conferenceService: ConferenceService) {
    suspend fun getData(): ApiDto? {
        return when (
            val response =
                safeApiCall { conferenceService.getData() }
        ) {
            is NetworkResult.Success -> {
                response.data
            }
            is NetworkResult.Error -> {
                null
            }
        }
    }

    private suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Api call failed $errorMessage")
}