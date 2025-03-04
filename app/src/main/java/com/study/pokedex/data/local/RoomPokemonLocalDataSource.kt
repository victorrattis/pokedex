package com.study.pokedex.data.local

import android.content.Context
import androidx.room.Room
import com.study.pokedex.data.IPokemonLocalDataSource
import kotlinx.coroutines.flow.Flow

class RoomPokemonLocalDataSource (
    private val applicationContext: Context
): IPokemonLocalDataSource {
    private val database by lazy { createDataBase() }

    override fun getAllPokemon(): Flow<List<PokemonEntity>> =
        database.pokemonDao().getAll()

    override suspend fun insertPokemon(vararg pokemon: PokemonEntity) =
        database.pokemonDao().insertAll(*pokemon)

    override suspend fun updatePokemon(vararg pokemon: PokemonEntity) {
        database.pokemonDao().update(*pokemon)
    }

    private fun createDataBase() = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "pokemon-database"
    ).build()
}