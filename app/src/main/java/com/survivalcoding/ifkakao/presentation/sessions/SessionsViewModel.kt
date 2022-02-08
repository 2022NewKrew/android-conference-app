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
    val sessions: LiveData<List<Session>> get() = _sessions
    private var _day = MutableLiveData(3)
    val day: LiveData<Int> get() = _day

    private var _fields = MutableLiveData<List<String>>(emptyList())
    private var _sbs = MutableLiveData<List<String>>(emptyList())
    private var _techs = MutableLiveData<List<String>>(emptyList())
    private var _companies = MutableLiveData<List<String>>(emptyList())

    private var _fieldFilter = MutableLiveData<List<String>>(emptyList())
    val fieldFilter: LiveData<List<String>> get() = _fieldFilter
    private var _sbFilter = MutableLiveData<List<String>>(emptyList())
    val sbFilter: LiveData<List<String>> get() = _sbFilter
    private var _techFilter = MutableLiveData<List<String>>(emptyList())
    val techFilter: LiveData<List<String>> get() = _techFilter
    private var _companyFilter = MutableLiveData<List<String>>(emptyList())
    val companyFilter: LiveData<List<String>> get() = _companyFilter

    init {
        viewModelScope.launch {
            _sessions.value = getSessionsByDay.invoke(_day.value!!)
        }
    }

    fun updateSessionsByDay(idx: Int) {
        if (idx == _day.value) return
        viewModelScope.launch {
            _day.value = idx
            _sessions.value = getSessionsByDay.invoke(idx)
        }
        resetFilters()
    }

    fun updateFieldFilter(filter: String, isAdding: Boolean) {
        _fieldFilter.value = if (isAdding) _fieldFilter.value!!.plus(filter)
        else _fieldFilter.value!!.minus(filter)
    }

    fun updateSbFilter(filter: String, isAdding: Boolean) {
        _sbFilter.value = if (isAdding) _sbFilter.value!!.plus(filter)
        else _sbFilter.value!!.minus(filter)
    }

    fun updateTechFilter(filter: String, isAdding: Boolean) {
        _techFilter.value = if (isAdding) _techFilter.value!!.plus(filter)
        else _techFilter.value!!.minus(filter)
    }

    fun updateCompanyFilter(filter: String, isAdding: Boolean) {
        _companyFilter.value = if (isAdding) _companyFilter.value!!.plus(filter)
        else _companyFilter.value!!.minus(filter)
    }

    fun initializeFilters() {
        _fieldFilter.value = _fields.value
        _sbFilter.value = _sbs.value
        _techFilter.value = _techs.value
        _companyFilter.value = _companies.value
    }

    fun resetFilters() {
        _fieldFilter.value = emptyList()
        _sbFilter.value = emptyList()
        _techFilter.value = emptyList()
        _companyFilter.value = emptyList()
        updateFilters()
    }

    fun updateFilters() {
        _fields.value = _fieldFilter.value
        _sbs.value = _sbFilter.value
        _techs.value = _techFilter.value
        _companies.value = _companyFilter.value
        updateSessionsByFilter()
    }

    private fun updateSessionsByFilter() {
        viewModelScope.launch {
            _sessions.value = getSessionsByFilter.invoke(
                _day.value!!,
                _fields.value!!,
                _sbs.value!!,
                _techs.value!!,
                _companies.value!!
            )
        }
    }
}