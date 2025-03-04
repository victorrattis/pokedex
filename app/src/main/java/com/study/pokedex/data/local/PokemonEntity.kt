package com.study.pokedex.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "detail") val detailUrl: String,
    @ColumnInfo(name = "isLoaded") val isLoaded: Boolean = false,
    @ColumnInfo(name = "types") val types: List<String>? = null,
    @ColumnInfo(name = "sprite") val sprite: String? = null,
    @ColumnInfo(name = "order") val order: Int? = null,
)


