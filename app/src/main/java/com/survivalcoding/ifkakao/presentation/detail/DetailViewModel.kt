package com.survivalcoding.ifkakao.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsByTagUseCase
import com.survivalcoding.ifkakao.presentation.FragmentInformation
import com.survivalcoding.ifkakao.presentation.base.UiState
import kotlinx.coroutines.flow.*

class DetailViewModel(
    private val getSessionsByTagUseCase: GetSessionsByTagUseCase,
) : ViewModel() {
    private val _relatedSessions by lazy {
        getSessionsByTagUseCase {
            it.field == _currentSession.value.field && it.id != _currentSession.value.id
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UiState.Loading
        )
    }
    private val _relatedSessionsCount = MutableStateFlow(4)
    private val _currentSession = MutableStateFlow(IkSessionData.getEmptySessionData())

//    val uiState by lazy {
//        _currentSession.combine(_relatedSessionsCount) { session, i ->
//            DetailUIState(_relatedSessions, i, session)
//        }.asLiveData()
//    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.LoadingData -> {
                _relatedSessionsCount.value = event.info.relatedSessionsCount
                _currentSession.value = event.info.currentSession
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

//class DetailViewModelFactory(
//    private val allSessions: List<IkSessionData>,
//) : ViewModelProvider.NewInstanceFactory() {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(DetailViewModel::class.java))
//            return DetailViewModel(
//                GetSessionsByTagUseCase(allSessions)
//            ) as T
//        return super.create(modelClass)
//    }
//}