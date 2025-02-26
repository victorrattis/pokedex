package com.study.pokedex.data.remote

import com.study.pokedex.data.remote.reponse.PokemonDetailResponse
import com.study.pokedex.data.remote.reponse.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeDexApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0
    ): PokemonListResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") pokemonName: String
    ): PokemonDetailResponse
}