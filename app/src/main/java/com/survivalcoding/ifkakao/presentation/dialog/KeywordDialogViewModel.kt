package com.survivalcoding.ifkakao.presentation.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.survivalcoding.ifkakao.domain.model.IkKeyword
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import com.survivalcoding.ifkakao.presentation.util.Keywords
import com.survivalcoding.ifkakao.presentation.util.toggle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import java.util.*
import javax.inject.Inject

@HiltViewModel
class KeywordDialogViewModel @Inject constructor(
    private val stk: Stack<FragmentInformation>
) : ViewModel() {
    private val _fields = MutableStateFlow(stk.peek().keywords.fieldList)
    private val _classification = MutableStateFlow(stk.peek().keywords.classificationList)
    private val _tech = MutableStateFlow(stk.peek().keywords.techClassificationList)
    private val _company = MutableStateFlow(stk.peek().keywords.companyList)

    val keywords = combine(_fields, _classification, _tech, _company) { a, b, c, d ->
        Keywords(a, b, c, d)
    }.asLiveData()

    fun onEvent(event: KeywordDialogEvent) {
        when (event) {
            is KeywordDialogEvent.ToggleClass -> {
                _classification.value = _classification.value.toggle(event.keyword)
            }
            is KeywordDialogEvent.ToggleCompany -> {
                _company.value = _company.value.toggle(event.keyword)
            }
            is KeywordDialogEvent.ToggleField -> {
                _fields.value = _fields.value.toggle(event.keyword)
            }
            is KeywordDialogEvent.ToggleTech -> {
                _tech.value = _tech.value.toggle(event.keyword)
            }
            KeywordDialogEvent.Init -> {
                val init = Keywords()
                _classification.value = init.classificationList
                _fields.value = init.fieldList
                _tech.value = init.techClassificationList
                _company.value = init.companyList
            }
            KeywordDialogEvent.Save -> {
                val top = stk.pop()
                stk.push(top.copy(keywords = keywords.value ?: Keywords()))
            }
        }
    }
}

sealed class KeywordDialogEvent {
    data class ToggleField(val keyword: IkKeyword) : KeywordDialogEvent()
    data class ToggleClass(val keyword: IkKeyword) : KeywordDialogEvent()
    data class ToggleTech(val keyword: IkKeyword) : KeywordDialogEvent()
    data class ToggleCompany(val keyword: IkKeyword) : KeywordDialogEvent()
    object Init : KeywordDialogEvent()
    object Save : KeywordDialogEvent()
}