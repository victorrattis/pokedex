package com.study.pokedex.data.remote

import com.study.pokedex.data.remote.reponse.PokemonDetailResponse
import com.study.pokedex.data.remote.reponse.PokemonListResponse
import com.study.pokedex.data.utils.LazySuspend
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeApiRemoteDataSource (
    private val baseUrl: String,
    private val scope: CoroutineScope
): IPokemonRemoteDataSource {
    private val pokeApi = LazySuspend { createPokeApi() }

    override suspend fun getPokemonList(
        limit: Int, offset: Int
    ): PokemonListResponse = scope.async {
        pokeApi.getValue().getPokemonList(limit, offset)
    }.await()

    override suspend fun getPokemonDetail(
        pokemonName: String
    ): PokemonDetailResponse = scope.async {
        pokeApi.getValue().getPokemonDetail(pokemonName)
    }.await()

    private fun createPokeApi() = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokeDexApi::class.java)
}
