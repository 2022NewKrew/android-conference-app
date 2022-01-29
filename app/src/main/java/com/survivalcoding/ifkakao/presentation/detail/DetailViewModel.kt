package com.survivalcoding.ifkakao.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetRelatedSessionsUseCase
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getRelatedSessionsUseCase: GetRelatedSessionsUseCase,
) : ViewModel() {
    private val _relatedSessionsCount = MutableStateFlow(4)
    private val _currentSession = MutableStateFlow(IkSessionData.getEmptySessionData())

    val relatedSessions =
        getRelatedSessionsUseCase(_currentSession.value).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = listOf()
        )
    val currentSession = _currentSession.asLiveData()
    val relatedSessionsCount = _relatedSessionsCount.asLiveData()

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.LoadingData -> {
                _relatedSessionsCount.value = event.info.exposedListCount
                _currentSession.value = event.info.session
            }
            DetailEvent.LoadMoreSessions -> {
                _relatedSessionsCount.value = _relatedSessionsCount.value + 10
            }
        }
    }

    fun getCount() = _relatedSessionsCount.value
}

sealed class DetailEvent {
    data class LoadingData(val info: FragmentInformation) : DetailEvent()
    object LoadMoreSessions : DetailEvent()
}