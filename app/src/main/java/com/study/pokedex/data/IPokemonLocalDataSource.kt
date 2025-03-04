package com.study.pokedex.data

import com.study.pokedex.data.local.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface IPokemonLocalDataSource {
    fun getAllPokemon(): Flow<List<PokemonEntity>>
    suspend fun insertPokemon(vararg pokemon: PokemonEntity)
    suspend fun updatePokemon(vararg pokemon: PokemonEntity)
}