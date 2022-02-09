package com.survivalcoding.ifkakao.presentation.session

import android.widget.RadioGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.domain.entity.*
import com.survivalcoding.ifkakao.domain.usecase.GetAllCategoriesUseCase
import com.survivalcoding.ifkakao.domain.usecase.GetSelectedSessionsUseCase
import com.survivalcoding.ifkakao.presentation.common.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val getSelectedSessionsUseCase: GetSelectedSessionsUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
) :
    ViewModel() {

    private val _sessionUiState =
        MutableStateFlow(SessionUiState(listOf(), listOf(), listOf(), listOf(), 0))
    val sessionUiState = _sessionUiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val selectedCategories = mutableSetOf<Category>()
    private var sortBy: SortBy = DEFAULT_SORT_BY

    init {
        viewModelScope.launch {
            resetSelectedCategories()
        }
    }

    private suspend fun buildDrawerBinderList(): List<CommonBinder> {
        val drawerBinderList = mutableListOf<CommonBinder>()
        val allCategories = getAllCategoriesUseCase()
        drawerBinderList.add(DrawerTitleBinder("정렬"))
        drawerBinderList.add(
            DrawerSortRadioGroupBinder(
                ::onSortRadioGroupCheckChange,
                ID_OF_DEFAULT_SORT_BY
            )
        )
        drawerBinderList.add(DrawerTitleBinder("관심분야"))
        drawerBinderList.add(KeywordToggleListBinder(allCategories.field.map {
            KeywordToggleBinder(
                category = it,
                selectedCategories = selectedCategories,
                onCheckedChange = ::onKeywordToggleCheckChange
            )
        }))
        drawerBinderList.add(DrawerTitleBinder("관심키워드"))
        drawerBinderList.add(DrawerSubtitleBinder("서비스·비즈니스"))
        drawerBinderList.add(KeywordToggleListBinder(allCategories.business.map {
            KeywordToggleBinder(
                category = it,
                selectedCategories = selectedCategories,
                onCheckedChange = ::onKeywordToggleCheckChange
            )
        }))
        drawerBinderList.add(DrawerSubtitleBinder("기술"))
        drawerBinderList.add(KeywordToggleListBinder(allCategories.tech.map {
            KeywordToggleBinder(
                category = it,
                selectedCategories = selectedCategories,
                onCheckedChange = ::onKeywordToggleCheckChange
            )
        }))
        drawerBinderList.add(DrawerSubtitleBinder("공동체분류"))
        drawerBinderList.add(KeywordToggleListBinder(allCategories.company.map {
            KeywordToggleBinder(
                category = it,
                selectedCategories = selectedCategories,
                onCheckedChange = ::onKeywordToggleCheckChange
            )
        }))

        return drawerBinderList
    }

    private fun onSortRadioGroupCheckChange(group: RadioGroup, checkedId: Int) {
        sortBy = when (checkedId) {
            R.id.sort_by_title_radio_button -> SortBy.TITLE
            R.id.sort_by_time_radio_button -> SortBy.TIME
            else -> DEFAULT_SORT_BY
        }
    }

    private fun onKeywordToggleCheckChange(isChecked: Boolean, category: Category) {
        if (isChecked) {
            selectedCategories.add(category)
        } else {
            selectedCategories.remove(category)
        }
        println(selectedCategories)
    }

    fun setCategoryFilter() {
        val categoriesBuilder = CategoriesBuilder()
        selectedCategories.map { categoriesBuilder.add(it) }
        val categories = categoriesBuilder.build()

        viewModelScope.launch {
            _sessionUiState.value = _sessionUiState.value.copy(
                day1Sessions = getSelectedSessionsUseCase(
                    1,
                    categories = categories,
                    sortBy = sortBy
                ),
                day2Sessions = getSelectedSessionsUseCase(
                    2,
                    categories = categories,
                    sortBy = sortBy
                ),
                day3Sessions = getSelectedSessionsUseCase(
                    3,
                    categories = categories,
                    sortBy = sortBy
                ).apply {
                    if (isEmpty()) sendEvent(Event.NoMatchingSessions)
                },
                selectedCategoryCount = categories.toList().size
            )
        }
    }

    fun resetSelectedCategories() {
        selectedCategories.clear()
        sortBy = DEFAULT_SORT_BY
        viewModelScope.launch {
            resetDrawerBinderList()
            setCategoryFilter()
        }
    }

    private suspend fun resetDrawerBinderList() {
        _sessionUiState.value = _sessionUiState.value.copy(
            drawerBinderList = buildDrawerBinderList()
        )
    }

    private fun sendEvent(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        object NoMatchingSessions : Event()
    }

    companion object {
        private val DEFAULT_SORT_BY = SortBy.TIME
        private const val ID_OF_DEFAULT_SORT_BY = R.id.sort_by_time_radio_button
    }
}

data class SessionUiState(
    val day1Sessions: List<Session>,
    val day2Sessions: List<Session>,
    val day3Sessions: List<Session>,
    val drawerBinderList: List<CommonBinder>,
    val selectedCategoryCount: Int
)