package com.survivalcoding.ifkakao.domain.model

data class Comment(
    val idx: Int,
    val nickname: String,
    val content: String,
    val replyCount: Int,
    val likeCount: Int
)
