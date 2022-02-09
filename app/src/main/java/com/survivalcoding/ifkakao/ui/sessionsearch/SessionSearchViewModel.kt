package com.survivalcoding.ifkakao.ui.sessionsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.Data
import com.survivalcoding.ifkakao.domain.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionSearchViewModel @Inject constructor(private val contentRepository: ContentRepository): ViewModel() {

    private val _sessions = MutableStateFlow<List<Data>>(listOf())
    val sessions = _sessions.asStateFlow()

    init {
        viewModelScope.launch {
            _sessions.value = contentRepository.getContent().data
        }
    }
}