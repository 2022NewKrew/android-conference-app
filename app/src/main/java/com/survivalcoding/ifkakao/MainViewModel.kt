package com.survivalcoding.ifkakao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Data
import com.example.domain.repository.ConferencesRepository
import com.example.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class MainViewModel @Inject constructor(
    private val getHighlightSessionsUseCase: GetHighlightSessionsUseCase,
    private val getAllSessionsUseCase: getAllSessionsUseCase,
    private val getLikedSessionsUseCase: GetLikedSessionsUseCase,
    private val getSessionsFromDateUseCase: GetSessionsFromDateUseCase,
    private val getSessionsWithKeyWordsUseCase: GetSessionsWithKeyWordsUseCase,
    private val getSessionWithFieldUseCase: GetSessionWithFieldUseCase
) : ViewModel() {

    val items = MutableStateFlow(listOf<Data>())
    lateinit var session: MutableStateFlow<Data>
        private set
    lateinit var relatedSessions: MutableStateFlow<List<Data>>
        private set

    init {
        viewModelScope.launch {
            items.value = getHighlightSessionsUseCase()
            session = MutableStateFlow(items.value[0])
            relatedSessions = MutableStateFlow(getSessionWithFieldUseCase(session.value.field))
        }
    }

    fun saveClickedSession(item: Data) {
        session.value = item
    }

    fun getRelatedSessions(field: String) {
        viewModelScope.launch {
            relatedSessions = MutableStateFlow(getSessionWithFieldUseCase(field))
        }
    }
}