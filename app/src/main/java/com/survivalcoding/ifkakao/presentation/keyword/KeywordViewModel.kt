package com.survivalcoding.ifkakao.presentation.keyword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsByTagUseCase
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import java.util.*
import javax.inject.Inject

@HiltViewModel
class KeywordViewModel @Inject constructor(
    private val getSessionsByTagUseCase: GetSessionsByTagUseCase,
    stk: Stack<FragmentInformation>
) : ViewModel() {
    private val _selectedKeyword = MutableStateFlow(stk.peek().selectedKeyword)
    private val _relatedSessionsCount = MutableStateFlow(8)

    val relatedSessions = combine(_selectedKeyword, _relatedSessionsCount) { keyword, count ->
        getSessionsByTagUseCase(keyword).take(count)
    }.asLiveData()
    val selectedKeyword = _selectedKeyword.asLiveData()
    val relatedSessionsCount = _relatedSessionsCount.asLiveData()

    fun onEvent(event: KeywordEvent) {
        when (event) {
            is KeywordEvent.LoadingData -> {
                _selectedKeyword.value = event.info.selectedKeyword
            }
            KeywordEvent.LoadMoreSessions -> {
                _relatedSessionsCount.value = _relatedSessionsCount.value + 10
            }
        }
    }

    fun getCount() = _relatedSessionsCount.value
}

sealed class KeywordEvent {
    data class LoadingData(val info: FragmentInformation) : KeywordEvent()
    object LoadMoreSessions : KeywordEvent()
}