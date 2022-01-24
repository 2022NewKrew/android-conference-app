package com.example.domain.entity


data class Data (
    val idx : Int,
    val createdUserIdx : Int,
    val createdDateTime : String,
    val lastModifiedUserIdx : Int,
    val lastModifiedDateTime : String,
    val categoryIdx : Int,
    val title : String,
    val content : String,
    val contentTag : String,
    val spotlightYn : String,
    val field : String,
    val sessionType : String,
    val commentYn : String,
    val company : String,
    val reservationDate : Int,
    val reservationTime : Int,
    val linkList : LinkList,
    val relationList : RelationList,
    val contentsSpeakerList : List<ContentsSpeakerList>,
    val favoriteYn : String,
    val newCountentsYn : String,
    val updateCountentsYn : String,
    val companyName : String,
    val speakerName : String,
    val speakerLoginYn : String,
    val reservationUTC : Int,
    val reservationYn : String,
    val videoYn : String
)