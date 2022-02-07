package com.example.domain.entity


data class Conference(
    val success: Boolean?,
    val code: Int?,
    val data: List<Data>?,
    val count: Int?,
    val errorMessage: String?
)