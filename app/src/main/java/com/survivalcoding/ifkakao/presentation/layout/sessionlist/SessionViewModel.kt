package com.survivalcoding.ifkakao.presentation.layout.sessionlist

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
class SessionViewModel @Inject constructor(private val repository: SessionRepository) : ViewModel() {
    var sessions by mutableStateOf(listOf<SessionItem>())
        private set

    init {
        viewModelScope.launch {
            sessions = (repository.getSessionList() ?: listOf()).filter { it.mainExposureDay == 1 }
        }
    }

    fun loadData(dayStr: String) {
        viewModelScope.launch {
            when (dayStr) {
                "Day1" -> {
                    sessions = (repository.getSessionList() ?: listOf()).filter { it.mainExposureDay == 1 }
                }
                "Day2" -> {
                    sessions = (repository.getSessionList() ?: listOf()).filter { it.mainExposureDay == 2 }
                }
                "Day3(All)" -> {
                    sessions = (repository.getSessionList() ?: listOf()) ?: listOf()
                }
            }
        }
    }
}
