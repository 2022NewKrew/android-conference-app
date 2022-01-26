package com.survivalcoding.ifkakao.presentation

import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.model.IkTagInfo

class UIState {
    /*
     * 전체 리스트,
     * 필터링 키워드,
     * 필터링한 리스트,
     * 현재 필요한 리스트 개수,
     * 노출할 리스트,
     * 선택된 세션,
     */

    private var allSessions = listOf<IkSessionData>()
    private var keywords = listOf<IkTagInfo>()
    private var _usingSessions = listOf<IkSessionData>()


    val usingSessions get() = _usingSessions
}