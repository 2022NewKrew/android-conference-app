package com.survivalcoding.ifkakao.presentation

import androidx.lifecycle.ViewModel
import com.survivalcoding.ifkakao.domain.model.IkListItem
import kotlinx.coroutines.flow.MutableStateFlow

class HighlightViewModel : ViewModel() {
    private val _highlightLists = MutableStateFlow<List<IkListItem>>(listOf())

}