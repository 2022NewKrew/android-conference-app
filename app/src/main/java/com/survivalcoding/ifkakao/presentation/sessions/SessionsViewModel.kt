package com.survivalcoding.ifkakao.presentation.sessions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SessionsViewModel @Inject constructor() : ViewModel() {
    private val _filterCount = MutableLiveData(0)
    val filterCount: LiveData<Int> get() = _filterCount
    private val _tmpSelectedField = MutableLiveData<MutableList<String>>()
    val tmpSelectedField: LiveData<MutableList<String>> get() = _tmpSelectedField
    private val _tmpSelectedKeyword = MutableLiveData<MutableList<String>>()
    val tmpSelectedKeyword: LiveData<MutableList<String>> get() = _tmpSelectedKeyword
    private val _tmpSelectedCompany = MutableLiveData<MutableList<String>>()
    val tmpSelectedCompany: LiveData<MutableList<String>> get() = _tmpSelectedCompany
    private val _selectedField = MutableLiveData<MutableList<String>>()
    val selectedField: LiveData<MutableList<String>> get() = _selectedField
    private val _selectedKeyword = MutableLiveData<MutableList<String>>()
    val selectedKeyword: LiveData<MutableList<String>> get() = _selectedKeyword
    private val _selectedCompany = MutableLiveData<MutableList<String>>()
    val selectedCompany: LiveData<MutableList<String>> get() = _selectedCompany

    fun selectField(field: String) {
        val fields: MutableList<String> = tmpSelectedField.value ?: mutableListOf()
        if (fields.contains(field)) fields.remove(field)
        else fields.add(field)
        _tmpSelectedField.value = fields
    }

    fun setField() {
        _selectedField.value = tmpSelectedField.value ?: mutableListOf()
    }

    fun selectKeyword(keyword: String) {
        val keywords: MutableList<String> = tmpSelectedKeyword.value ?: mutableListOf()
        if (keywords.contains(keyword)) keywords.remove(keyword)
        else keywords.add(keyword)
        _tmpSelectedKeyword.value = keywords
    }

    fun setKeyword() {
        _selectedKeyword.value = tmpSelectedKeyword.value ?: mutableListOf()
    }

    fun selectCompany(company: String) {
        val companies: MutableList<String> = tmpSelectedCompany.value ?: mutableListOf()
        if (companies.contains(company)) companies.remove(company)
        else companies.add(company)
        _tmpSelectedCompany.value = companies
    }

    fun setCompany() {
        _selectedCompany.value = tmpSelectedCompany.value ?: mutableListOf()
    }

    fun calculateCount() {
        _filterCount.value = (selectedField.value ?: mutableListOf()).size + (selectedKeyword.value
            ?: mutableListOf()).size + (selectedCompany.value ?: mutableListOf()).size
    }

    fun setTmpFilter() {
        _tmpSelectedField.value = selectedField.value ?: mutableListOf()
        _tmpSelectedKeyword.value = selectedKeyword.value ?: mutableListOf()
        _tmpSelectedCompany.value = selectedCompany.value ?: mutableListOf()
    }

    fun resetFilter() {
        _tmpSelectedField.value = mutableListOf()
        _tmpSelectedKeyword.value = mutableListOf()
        _tmpSelectedCompany.value = mutableListOf()
        _selectedField.value = mutableListOf()
        _selectedKeyword.value = mutableListOf()
        _selectedCompany.value = mutableListOf()
        _filterCount.value = 0
    }
}