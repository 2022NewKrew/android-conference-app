package com.survivalcoding.ifkakao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.ContentState
import com.example.domain.entity.Data
import com.example.domain.entity.OrderState
import com.example.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class MainViewModel @Inject constructor(
    private val getHighlightSessionsUseCase: GetHighlightSessionsUseCase,
    private val getAllSessionsUseCase: GetAllSessionsUseCase,
    private val getLikedSessionsUseCase: GetLikedSessionsUseCase,
    private val getSessionsWithKeyWordsUseCase: GetSessionsWithKeyWordsUseCase,
    private val getSessionWithFieldUseCase: GetSessionWithFieldUseCase,
    private val getSortedSessionsUseCase: GetSortedSessionsUseCase
) : ViewModel() {

    val highlightItems = MutableStateFlow(listOf<Data>())
    val daysItems = MutableStateFlow(listOf<Data>())
    lateinit var session: MutableStateFlow<Data>
    val relatedSessions = MutableStateFlow(listOf<Data>())

    init {
        viewModelScope.launch {
            daysItems.value = getAllSessionsUseCase() ?: listOf()

            highlightItems.value = getHighlightSessionsUseCase() ?: listOf()

            session = MutableStateFlow(highlightItems.value[0])
            relatedSessions.value = session.value.field?.let {
                getSessionWithFieldUseCase(it)
            } ?: listOf()

        }
    }

    fun saveClickedSession(item: Data) {
        session.value = item
    }

    fun getSortedDateSession(date: Int, contentState: ContentState, orderState: OrderState) {
        viewModelScope.launch {
            daysItems.value =
                getSortedSessionsUseCase(date, contentState, orderState) ?: listOf()
        }
    }


    fun getRelatedSessions(field: String) {
        viewModelScope.launch {
            relatedSessions.value = session.value.field?.let {
                getSessionWithFieldUseCase(it)
            } ?: listOf()
        }
    }
}