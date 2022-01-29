package com.survivalcoding.ifkakao.presentation.highlight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetHighlightSessionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HighlightViewModel @Inject constructor(
    getHighlightSessionsUseCase: GetHighlightSessionsUseCase,
) : ViewModel() {
    val uiState: StateFlow<List<IkSessionData>> = getHighlightSessionsUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = listOf()
        )
}