package com.survivalcoding.ifkakao.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val contentRepository: ContentRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState(listOf()))
    val uiState = _uiState.asStateFlow()

    fun getSpotlightSessions() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(sessions = contentRepository.getContent().data.filter { it.spotlightYn == "Y" })
        }
    }
}