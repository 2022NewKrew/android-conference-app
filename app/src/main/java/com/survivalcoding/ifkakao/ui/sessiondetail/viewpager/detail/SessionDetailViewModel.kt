package com.survivalcoding.ifkakao.ui.sessiondetail.viewpager.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.Data
import com.survivalcoding.ifkakao.domain.repository.ContentRepository
import com.survivalcoding.ifkakao.ui.session.SessionFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionDetailViewModel @Inject constructor(private val contentRepository: ContentRepository): ViewModel() {
    private val _relatedSessions = MutableStateFlow<List<Data>>(listOf())
    val relatedSessions = _relatedSessions.asStateFlow()

    fun getRelateSessions(sessionFilter: SessionFilter) {
        viewModelScope.launch {
            _relatedSessions.value = sessionFilter.filter(contentRepository.getContent().data)
        }
    }
}