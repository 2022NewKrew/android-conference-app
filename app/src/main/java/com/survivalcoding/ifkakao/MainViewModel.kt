package com.survivalcoding.ifkakao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.ContentState
import com.example.domain.entity.Data
import com.example.domain.entity.OrderState
import com.example.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class MainViewModel @Inject constructor(
    private val getHighlightSessionsUseCase: GetHighlightSessionsUseCase,
    private val getAllSessionsUseCase: GetAllSessionsUseCase,
    private val getLikedSessionsUseCase: GetLikedSessionsUseCase,
    private val getSessionsWithKeyWordsAndDateUseCase: GetSessionsWithKeyWordsAndDateUseCase,
    private val getSessionWithFieldUseCase: GetSessionWithFieldUseCase,
    private val getSortedSessionsUseCase: GetSortedSessionsUseCase,
    private val getLikeInfoUseCase: GetLikeInfoUseCase,
    private val addLikeUseCase: AddLikeUseCase,
    private val deleteLikeUseCase: DeleteLikeUseCase,
    private val getRelatedSessionUseCase: GetRelatedSessionUseCase
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
        val classification = session.value.relationList?.classification ?: listOf()
        val techClassification = session.value.relationList?.techClassification ?: listOf()
        val mergedClassification = classification + techClassification
        getRelatedSessions(mergedClassification)
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

    //todo searchFragment 에서는 한 usecase만 쓰기 day- sorting - filter 연결되게
    private fun getSessionsWithKeyWords() {
        viewModelScope.launch {
            daysItems.value =
                if (keywords.value.isEmpty()) getSortedSessionsUseCase(
                    date.value,
                    contentState,
                    orderState
                ) ?: listOf()
                else getSessionsWithKeyWordsAndDateUseCase(date.value, keywords.value) ?: listOf()
        }
    }

    private fun getRelatedSessions(classification: List<String>?) {
        viewModelScope.launch(Dispatchers.IO) {
            classification?.let { techList ->
                relatedSessions.value = getRelatedSessionUseCase(techList) ?: listOf()
            }
        }
    }
}