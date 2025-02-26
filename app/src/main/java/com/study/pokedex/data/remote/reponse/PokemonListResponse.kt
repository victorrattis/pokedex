package com.study.pokedex.data.remote.reponse

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("count")
    val count: Int,

    @SerializedName("results")
    val results: List<PokemonItemResponse>
)