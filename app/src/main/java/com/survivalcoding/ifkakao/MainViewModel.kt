package com.survivalcoding.ifkakao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.ContentState
import com.example.domain.entity.Data
import com.example.domain.entity.OrderState
import com.example.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class MainViewModel @Inject constructor(
    private val getHighlightSessionsUseCase: GetHighlightSessionsUseCase,
    private val getAllSessionsUseCase: GetAllSessionsUseCase,
    private val getLikedSessionsUseCase: GetLikedSessionsUseCase,
    private val getSessionsWithKeyWordsUseCase: GetSessionsWithKeyWordsUseCase,
    private val getSessionWithFieldUseCase: GetSessionWithFieldUseCase,
    private val getSortedSessionsUseCase: GetSortedSessionsUseCase,
    private val getLikeInfoUseCase: GetLikeInfoUseCase,
    private val addLikeUseCase: AddLikeUseCase,
    private val deleteLikeUseCase: DeleteLikeUseCase
) : ViewModel() {

    val isLogin = MutableStateFlow(false)
    val id = MutableStateFlow<String?>(null)
    val keywords = MutableStateFlow(listOf<String>())
    val date = MutableStateFlow<Int>(20211116)
    private var contentState: ContentState = ContentState.title
    private var orderState: OrderState = OrderState.asc
    lateinit var session: MutableStateFlow<Data>

    private val _likedList: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())
    val likedList get() = _likedList
    val highlightItems = MutableStateFlow(listOf<Data>())
    val daysItems = MutableStateFlow(listOf<Data>())
    val relatedSessions = MutableStateFlow(listOf<Data>())

    init {
        viewModelScope.launch {
            daysItems.value = getAllSessionsUseCase() ?: listOf()

            highlightItems.value = getHighlightSessionsUseCase() ?: listOf()

            session = MutableStateFlow(highlightItems.value[0])
            //todo 연관세션 안하고 있음
            relatedSessions.value = session.value.field?.let {
                getSessionWithFieldUseCase(it)
            } ?: listOf()
        }
    }

    fun setLogin(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            isLogin.value = true
            id.value = name
            getLikeInfoUseCase(name).collect {
                println(it)
                _likedList.value = it
            }
        }
    }

    fun setLogout() {
        viewModelScope.launch(Dispatchers.IO) {
            isLogin.value = false
            id.value = null
            likedList.value = emptyList()
        }
    }

    fun toggleLike(idx: Int, isLike: Boolean) {
        if (isLogin.value && id.value != null) {
            viewModelScope.launch(Dispatchers.IO) {
                if (isLike) {
                    println("------isLike-------")
                    println(isLike)
                    println(idx)
                    addLikeUseCase(id.value!!, idx)
                } else {
                    println("------disLike-------")
                    println(isLike)
                    println(idx)
                    deleteLikeUseCase(id.value!!, idx)
                }
            }
        }
    }

    fun saveClickedSession(item: Data) {
        session.value = item
    }

    fun getSortedDateSession(_contentState: ContentState, _orderState: OrderState) {
        contentState = _contentState
        orderState = _orderState
        viewModelScope.launch {
            daysItems.value =
                getSortedSessionsUseCase(date.value, contentState, orderState) ?: listOf()
        }
    }

    fun updateKeyWords(newKeyWords: List<String>) {
        println(newKeyWords)
        println(keywords.value)
        if (newKeyWords.size != keywords.value.size) {
            keywords.value = newKeyWords
            getSessionsWithKeyWords()
        } else {
            for ((idx, value) in newKeyWords.withIndex()) {
                if (value != keywords.value[idx]) {
                    keywords.value = newKeyWords
                    getSessionsWithKeyWords()
                }
            }
        }
    }

    private fun getSessionsWithKeyWords() {
        viewModelScope.launch {
            daysItems.value =
                if (keywords.value.isEmpty()) getSortedSessionsUseCase(
                    date.value,
                    contentState,
                    orderState
                ) ?: listOf()
                else getSessionsWithKeyWordsUseCase(date.value, keywords.value) ?: listOf()
        }
    }

    fun getRelatedSessions(field: String) {
        viewModelScope.launch {
            relatedSessions.value = session.value.field?.let {
                getSessionWithFieldUseCase(it)
            } ?: listOf()
        }
    }
}