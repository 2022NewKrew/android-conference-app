package com.survivalcoding.ifkakao.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.Data
import com.survivalcoding.ifkakao.domain.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import java.util.function.BiPredicate
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(private val contentRepository: ContentRepository) :
    ViewModel() {

    private val _dayFilter = MutableStateFlow(1)
    private val _fieldFilters = MutableStateFlow(mapOf<String, Boolean>())
    private val _classFilters = MutableStateFlow(mapOf<String, Boolean>())
    private val _techFilters = MutableStateFlow(mapOf<String, Boolean>())
    private val _companyFilters = MutableStateFlow(mapOf<String, Boolean>())

    private val _sessionFilter = combine(
        _dayFilter,
        _fieldFilters,
        _classFilters,
        _techFilters,
        _companyFilters
    ) { dayFilter, fieldFilters, classFilters, techFilters, companyFilters ->
        SessionFilter(dayFilter, fieldFilters, classFilters, techFilters, companyFilters)
    }

    private val _sessions = MutableStateFlow(listOf<Data>())

    val uiState = combine(_sessions, _sessionFilter) { sessions, sessionFilter ->
        SessionUiState(sessions, sessionFilter)
    }

    fun getSessions() {
        viewModelScope.launch {
            val sessions = contentRepository.getContent().data
            _sessions.value = sessions
            _dayFilter.value = 1

            val fieldFilters = mutableListOf<String>()
            val classFilters = mutableListOf<String>()
            val techFilters = mutableListOf<String>()
            val companyFilters = mutableListOf<String>()

            sessions.forEach {
                fieldFilters += it.field
                classFilters += it.relationList.CLASSIFICATION
                techFilters += it.relationList.TECH_CLASSIFICATION
                companyFilters += it.companyName
            }

            _fieldFilters.value = fieldFilters.toSet().associateWith { false }
            _classFilters.value = classFilters.toSet().associateWith { false }
            _techFilters.value = techFilters.toSet().associateWith { false }
            _companyFilters.value = companyFilters.toSet().associateWith { false }
        }
    }

    fun applyDayFilter(day: Int) {
        _dayFilter.value = day
    }

    fun applyFilter(
        fieldFilters: Map<String, Boolean>,
        classFilters: Map<String, Boolean>,
        techFilters: Map<String, Boolean>,
        companyFilters: Map<String, Boolean>
    ) {
        _fieldFilters.value = fieldFilters
        _classFilters.value = classFilters
        _techFilters.value = techFilters
        _companyFilters.value = companyFilters
    }
}