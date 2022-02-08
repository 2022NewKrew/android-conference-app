package com.survivalcoding.ifkakao.presentation.sessions

data class FilterUiState(
    var fields: List<String>,
    var classifications: List<String>,
    var techClassifications: List<String>,
    val companies: List<String>,
)