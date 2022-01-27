package com.survivalcoding.ifkakao.presentation.keyword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.model.IkTagInfo
import com.survivalcoding.ifkakao.domain.model.TagType
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsByTagUseCase
import com.survivalcoding.ifkakao.presentation.FragmentInformation
import com.survivalcoding.ifkakao.presentation.detail.DetailEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class KeywordViewModel @Inject constructor(
    private val getSessionsByTagUseCase: GetSessionsByTagUseCase,
) : ViewModel() {
    val relatedSessions by lazy {
        getSessionsByTagUseCase {
            when (_selectedKeyword.value.tagType) {
                TagType.COMPANY -> it.company == _selectedKeyword.value.text
                TagType.FIELD -> it.field == _selectedKeyword.value.text
                TagType.CLASSIFICATION -> it.tag.any { tag -> tag.text == _selectedKeyword.value.text }
                TagType.TECH_CLASSIFICATION -> it.tag.any { tag -> tag.text == _selectedKeyword.value.text }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = listOf()
        )
    }
    private val _selectedKeyword = MutableStateFlow(IkTagInfo.getEmptyTagInfo())
    private val _relatedSessionsCount = MutableStateFlow(8)

    val selectedKeyword = _selectedKeyword.asLiveData()
    val relatedSessionsCount = _relatedSessionsCount.asLiveData()

    fun onEvent(event: KeywordEvent) {
        when (event) {
            is KeywordEvent.LoadingData -> {
                _selectedKeyword.value = event.info.selectedKeyword
            }
            KeywordEvent.LoadMoreSessions -> {
                _relatedSessionsCount.value = _relatedSessionsCount.value + 10
            }
        }
    }

    fun getCount() = _relatedSessionsCount.value
}

sealed class KeywordEvent {
    data class LoadingData(val info: FragmentInformation) : KeywordEvent()
    object LoadMoreSessions : KeywordEvent()
}