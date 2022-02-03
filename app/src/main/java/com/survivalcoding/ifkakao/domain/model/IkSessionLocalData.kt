package com.survivalcoding.ifkakao.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity
data class IkSessionLocalData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val comments: List<IkComment> = listOf(),
    val isLiked: Boolean = false,
)

@ProvidedTypeConverter
class CommentTypeConverter(private val gson: Gson) {
    @TypeConverter
    fun commentToJson(value: IkComment): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToComment(value: String): IkComment {
        return gson.fromJson(value, IkComment::class.java)
    }
}

@ProvidedTypeConverter
class CommentListTypeConverter(private val gson: Gson) {
    @TypeConverter
    fun listToJson(value: List<IkComment>): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<IkComment> {
        return gson.fromJson(value, Array<IkComment>::class.java).toList()
    }
}