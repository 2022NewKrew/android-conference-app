package com.survivalcoding.ifkakao.ui.session.dialog

import androidx.lifecycle.ViewModel
import com.survivalcoding.ifkakao.domain.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterDialogViewModel @Inject constructor(private val contentRepository: ContentRepository): ViewModel() {

    fun getFilterFieldItems(): List<String> {
        return contentRepository.getFilterFieldItems()
    }

    fun getFilterClassificationItems(): List<String> {
        return contentRepository.getFilterClassificationItems()
    }

    fun getFilterTechnicalClassificationItems(): List<String> {
        return contentRepository.getFilterTechnicalClassificationItems()
    }

    fun getFilterCompanyItems(): List<String> {
        return contentRepository.getFilterCompanyItems()
    }
}