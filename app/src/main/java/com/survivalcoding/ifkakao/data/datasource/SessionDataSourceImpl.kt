package com.survivalcoding.ifkakao.data.datasource

import com.survivalcoding.ifkakao.data.dto.ApiDto
import com.survivalcoding.ifkakao.data.vo.DataVo
import com.survivalcoding.ifkakao.domain.model.SessionItem
import com.survivalcoding.ifkakao.domain.model.SpeakerProfileItem
import com.survivalcoding.ifkakao.domain.repository.NetworkResult
import retrofit2.Response
import javax.inject.Inject

class SessionDataSourceImpl @Inject constructor(private val sessionService: SessionService) :
    SessionDataSource {
    private suspend fun getApiDto(): ApiDto? {
        return when (
            val response =
                safeApiCall { sessionService.getData() }
        ) {
            is NetworkResult.Success -> {
                response.data
            }
            is NetworkResult.Error -> {
                null
            }
        }
    }

    private suspend fun getData(): List<DataVo>? {
        return getApiDto()?.data
    }

    override suspend fun getSessionList(): List<SessionItem>? {
        return when (val data = getData()) {
            null -> null
            else -> {
                val ret = mutableListOf<SessionItem>()
                data.forEach { it ->
                    with(it) {
                        val mainImageList =
                            this.linkList.MO_MAIN_IMAGE.filter { moMainImageVo ->
                                moMainImageVo.type == "MO_MAIN_IMAGE"
                            }
                        val mainImage = if (mainImageList.isEmpty()) null else mainImageList.first()
                        val imageList =
                            this.linkList.MO_IMAGE.filter { moMainImageVo -> moMainImageVo.type == "MO_MAIN_IMAGE" }
                        val image = if (imageList.isEmpty()) null else imageList.first()

                        val newItem = SessionItem(
                            idx = idx,
                            title = title,
                            content = content,
                            company = company,
                            field = field,
                            contentTag = contentTag.split(" ").map { hashContainingName ->
                                hashContainingName.removePrefix("#")
                            },
                            videoUrl = linkList.VIDEO.first { videoVo -> videoVo.type == "VIDEO" }.url,
                            videoDuration = linkList.VIDEO.first { videoVo -> videoVo.type == "VIDEO" }.description,
                            mainImageUrl = mainImage?.url ?: image?.url ?: "",
                            imageUrl = image?.url ?: mainImage?.url ?: "",
                            classifications = relationList.CLASSIFICATION,
                            techClassifications = relationList.TECH_CLASSIFICATION,
                            mainExposureDay = relationList.MAIN_EXPOSURE_DAY.let { dayStr ->
                                when {
                                    dayStr.contains("1Day") -> {
                                        return@let 1
                                    }
                                    dayStr.contains("2Day") -> {
                                        return@let 2
                                    }
                                    else -> {
                                        return@let 3
                                    }
                                }
                            },
                            speakerProfiles = contentsSpeakerList.zip(linkList.SPEAKER_PROFILE) { a, b ->
                                SpeakerProfileItem(
                                    idx = a.idx,
                                    profileImageUrl = b.url,
                                    nameKo = a.nameKo,
                                    nameEn = a.nameEn,
                                    company = a.company,
                                    occupation = a.occupation,
                                    channelLink = a.channelLink
                                )
                            },
                            isHighlight = spotlightYn.uppercase() == "Y"
                        )
                        ret.add(newItem)
                    }
                }
                ret
            }
        }
    }

    override suspend fun getSessionItem(idx: Int): SessionItem? {
        TODO("Not yet implemented")
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