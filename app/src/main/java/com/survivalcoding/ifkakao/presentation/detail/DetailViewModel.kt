package com.survivalcoding.ifkakao.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsByTagUseCase
import com.survivalcoding.ifkakao.presentation.FragmentInformation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class DetailViewModel(
    private val getSessionsByTagUseCase: GetSessionsByTagUseCase,
) : ViewModel() {
    private var _relatedSessions = listOf<IkSessionData>()
    private val _relatedSessionsCount = MutableStateFlow(4)
    private val _currentSession = MutableStateFlow(IkSessionData.getEmptySessionData())

    val uiState = combine(_relatedSessionsCount, _currentSession) { i, session ->
        DetailUIState(_relatedSessions, i, session)
    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.LoadingData -> {
                _relatedSessionsCount.value = event.info.relatedSessionsCount
                _currentSession.value = event.info.currentSession
                _relatedSessions = getSessionsByTagUseCase {
                    it.field == _currentSession.value.field && it.id != _currentSession.value.id
                }
            }
            DetailEvent.LoadMoreSessions -> {
                _relatedSessionsCount.value = _relatedSessionsCount.value + 10
            }
        }
    }

    fun getSize() = _relatedSessionsCount.value
    fun getVideoThumbnailUrl() = _currentSession.value.video.thumbnailUrl
}

sealed class DetailEvent {
    data class LoadingData(val info: FragmentInformation) : DetailEvent()
    object LoadMoreSessions : DetailEvent()
}

class DetailViewModelFactory(
    private val allSessions: List<IkSessionData>,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java))
            return DetailViewModel(
                GetSessionsByTagUseCase(allSessions)
            ) as T
        return super.create(modelClass)
    }
}