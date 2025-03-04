package com.study.pokedex.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    fun getAll(): Flow<List<PokemonEntity>>

    @Insert
    fun insertAll(vararg pokemon: PokemonEntity)

    @Update
    fun update(vararg pokemon: PokemonEntity)

    @Delete
    fun delete(pokemon: PokemonEntity)
}