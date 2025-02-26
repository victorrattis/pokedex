package com.study.pokedex.data.remote.reponse

import com.google.gson.annotations.SerializedName

data class PokemonItemResponse(
    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String
)
