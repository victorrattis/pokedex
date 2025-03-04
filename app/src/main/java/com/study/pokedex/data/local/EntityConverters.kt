package com.study.pokedex.data.local

import androidx.room.TypeConverter

class EntityConverters {
    @TypeConverter
    fun fromList(types: List<String>): String = types.joinToString(",")

    @TypeConverter
    fun toList(data: String): List<String> = data.split(",")
}