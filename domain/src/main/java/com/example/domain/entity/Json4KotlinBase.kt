package com.example.domain.entity


data class Json4KotlinBase(

    val success: Boolean,
    val code: Int,
    val data: List<Conference>,
    val count: Int,
    val errorMessage: String
)