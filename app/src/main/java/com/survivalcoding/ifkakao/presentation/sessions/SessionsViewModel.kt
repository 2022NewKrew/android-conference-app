package com.survivalcoding.ifkakao.presentation.sessions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsByDayUseCase
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsByFilterUseCase
import kotlinx.coroutines.launch

class SessionsViewModel(
    private val getSessionsByFilter: GetSessionsByFilterUseCase,
    private val getSessionsByDay: GetSessionsByDayUseCase
) : ViewModel() {
    private var _sessions = MutableLiveData<List<Session>>(emptyList())
    val sessions: LiveData<List<Session>> = _sessions
    private var _day = MutableLiveData(3)
    val day: LiveData<Int> = _day

    private var _fields = MutableLiveData<List<String>>(emptyList())
    val fields: LiveData<List<String>> = _fields
    private var _classifications = MutableLiveData<List<String>>(emptyList())
    val classifications: LiveData<List<String>> = _classifications
    private var _techClassifications = MutableLiveData<List<String>>(emptyList())
    val techClassifications: LiveData<List<String>> = _techClassifications
    private var _companies = MutableLiveData<List<String>>(emptyList())
    val companies: LiveData<List<String>> = _companies

    fun updateSessionsByDay(idx: Int) {
        viewModelScope.launch {
            _day.value = idx
            _sessions.value = getSessionsByDay.invoke(idx)
        }
    }

    fun updateSessionsByFilter(
        field: List<String>,
        classification: List<String>,
        techClassification: List<String>,
        companies: List<String>
    ) {
        _fields.value = field
        _classifications.value = classification
        _techClassifications.value = techClassification
        _companies.value = companies
        viewModelScope.launch {
            _sessions.value = getSessionsByFilter.invoke(
                _sessions.value!!,
                _fields.value!!,
                _classifications.value!!,
                _techClassifications.value!!,
                _companies.value!!
            )
        }
    }


}