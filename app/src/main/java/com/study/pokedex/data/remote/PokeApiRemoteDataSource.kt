package com.study.pokedex.data.remote

import com.study.pokedex.data.remote.reponse.PokemonDetailResponse
import com.study.pokedex.data.remote.reponse.PokemonListResponse
import com.study.pokedex.data.utils.LazySuspend
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeApiRemoteDataSource (
    private val baseUrl: String
): IPokemonRemoteDataSource {
    private val pokeApi = LazySuspend { createPokeApi() }

    override suspend fun getPokemonList(limit: Int, offset: Int): PokemonListResponse {
        // TODO: Use pagination or another solution to load all the data
        return pokeApi.getValue().getPokemonList(limit, offset)
    }

    override suspend fun getPokemonDetail(pokemonName: String): PokemonDetailResponse {
        return pokeApi.getValue().getPokemonDetail(pokemonName)
    }

    private fun createPokeApi() = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokeDexApi::class.java)
}