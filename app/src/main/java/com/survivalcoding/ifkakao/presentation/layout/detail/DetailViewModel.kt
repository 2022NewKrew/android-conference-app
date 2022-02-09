package com.survivalcoding.ifkakao.presentation.layout.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.SessionItem
import com.survivalcoding.ifkakao.domain.model.mock
import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: SessionRepository) : ViewModel() {
    private val idx: MutableLiveData<Int> = MutableLiveData()
    var sessionItem by mutableStateOf(SessionItem.mock())
        private set
    var relatedSessionList by mutableStateOf(listOf<SessionItem>())

    fun getIdxValue() = idx.value
    fun setIdxValue(newIdx: Int) {
        idx.value = newIdx
        viewModelScope.launch {
            sessionItem = repository.getSessionItem(idx.value!!) ?: sessionItem
            loadRelatedSessions()
        }
    }

    private fun loadRelatedSessions() {
        val field = sessionItem.field
        val tech = sessionItem.techClassifications
        val ret = mutableListOf<SessionItem>()
        viewModelScope.launch {
            repository.getSessionList()?.forEach {
                if (it.field == field) {
                    ret.add(it)
                } else {
                    it.techClassifications.forEach { target ->
                        tech.forEach { original ->
                            if (target == original) {
                                ret.add(it)
                            }
                        }
                    }
                }
            }
            relatedSessionList = ret
        }
    }
}