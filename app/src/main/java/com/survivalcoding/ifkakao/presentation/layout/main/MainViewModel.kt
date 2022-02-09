package com.survivalcoding.ifkakao.presentation.layout.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.SessionItem
import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: SessionRepository) : ViewModel() {
    var sessions by mutableStateOf(listOf<SessionItem>())
        private set

    init {
        viewModelScope.launch {
            sessions = (repository.getSessionList() ?: listOf()).filter { it.isHighlight }
        }
    }
}