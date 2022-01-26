package com.survivalcoding.ifkakao.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import java.util.*

class NewMainViewModel(
    private val getSessionsUseCase: GetSessionsUseCase
) : ViewModel() {
    // 모든 프래그먼트에서 공통으로 사용할 데이터
    // all sessions, page stack
    private val _allSessions = MutableStateFlow<List<IkSessionData>>(listOf())
    private val _fragmentStack = Stack<FragmentInfoSummary>().apply {
        push(FragmentInfoSummary(FragmentType.HIGHLIGHT))
    }

    init {
        runBlocking { _allSessions.value = getSessionsUseCase() }
    }

    /*
     * fragment process
     * 1. INIT -> LOADING           :: stk의 top information을 복사한다.
     * 2. LOADING                   :: error check: fragment type과 top information의 타입 비교.
     * 3. LOADING                   :: 복사한 information을 기반으로 데이터 구성.
     * 4. LOADING -> NOT LOADING    :: 데이터 구성 끝.
     *
     * -- 다른 프래그먼트로 이동할 때 --
     * 1. stk의 top은 항상 현재 프래그먼트의 정보.
     * 2. 이동하기 전, 현재 상태를 업데이트한다. (pop -> copy -> push)
     * 3. 다음 페이지에 필요한 정보들로 information을 만든다.
     * 4. stk에 push하고 프래그먼트 이동
     *
     * -- 뒤로가기 눌렀을 때 --
     * 1. stk의 top에 있는 정보는 필요 없어짐.
     * 2. stk pop
     * 3. 프래그먼트 이동.
     */

    private val _state = MutableStateFlow<State>(State.NotLoading)
    val state = _state.asLiveData()
    fun setState(state: State) {
        _state.value = state
    }

    private val _fragmentInfo =
        MutableStateFlow<FragmentInformation>(HighlightFragmentInformation())

    fun onEvent(event: Event) {
        when (event) {
            Event.INITIALIZE -> {
                _fragmentInfo.value = _fragmentStack.peek().getInformation()
                when (val tmp = _state.value) {
                    is State.Init -> {
                        if (tmp.fragmentType != _fragmentInfo.value.fragmentType) {
                            _state.value = State.Error("Stack System Error")
                            return
                        }
                    }
                    else -> {
                        _state.value = State.Error("State Error")
                        return
                    }
                }
                _state.value = State.Loading
            }
            Event.LOAD_START -> TODO()
            Event.LOAD_MORE_SESSIONS -> TODO()
        }
    }
}

enum class Event {
    INITIALIZE, LOAD_START, LOAD_MORE_SESSIONS;
}

sealed class State {
    data class Init(val fragmentType: FragmentType) : State()
    object Loading : State()
    object NotLoading : State()
    data class Error(val message: String) : State()
}