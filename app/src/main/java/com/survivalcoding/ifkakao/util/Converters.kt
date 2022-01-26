package com.survivalcoding.ifkakao.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.survivalcoding.ifkakao.domain.model.ContentsSpeaker
import com.survivalcoding.ifkakao.domain.model.LinkList
import com.survivalcoding.ifkakao.domain.model.RelationList

class Converters {
    private val gson: Gson = Gson()

    @TypeConverter
    fun fromLinkList(value: LinkList): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToLinkList(value: String): LinkList {
        return gson.fromJson(value, LinkList::class.java)
    }

    @TypeConverter
    fun fromRelationList(value: RelationList): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToRelationList(value: String): RelationList {
        return gson.fromJson(value, RelationList::class.java)
    }

    @TypeConverter
    fun fromContentsSpeakerList(value: List<ContentsSpeaker>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToContentsSpeakerList(value: String): List<ContentsSpeaker> {
        return gson.fromJson(value, Array<ContentsSpeaker>::class.java).toList()
    }
}