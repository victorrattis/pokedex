package com.study.pokedex.data.remote.reponse

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("order")
    val order: Int,

    @SerializedName("sprites")
    val sprites: PokemonSpriteResponse,

    @SerializedName("types")
    val types: List<PokemonTypeResponse>
)

data class PokemonTypeResponse(
    @SerializedName("slot")
    val slot: Int,

    @SerializedName("type")
    val type: TypeResponse
)

data class TypeResponse(
    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String
)