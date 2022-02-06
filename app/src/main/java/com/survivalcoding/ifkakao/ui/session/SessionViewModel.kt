package com.survivalcoding.ifkakao.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.repository.ContentRepository
import com.survivalcoding.ifkakao.ui.main.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(private val contentRepository: ContentRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(SessionUiState(listOf()))
    val uiState = _uiState.asStateFlow()

    fun getSessions() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(sessions = contentRepository.getContent().data.filter { it.spotlightYn == "Y" })
        }
    }
}