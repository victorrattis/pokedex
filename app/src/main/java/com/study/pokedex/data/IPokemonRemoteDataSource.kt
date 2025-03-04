package com.study.pokedex.data

import com.study.pokedex.data.remote.reponse.PokemonDetailResponse
import com.study.pokedex.data.remote.reponse.PokemonListResponse

interface IPokemonRemoteDataSource {
    suspend fun getPokemonList(limit: Int, offset: Int): PokemonListResponse
    suspend fun getPokemonDetail(pokemonName: String): PokemonDetailResponse
}