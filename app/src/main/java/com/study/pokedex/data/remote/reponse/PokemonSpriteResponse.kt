package com.study.pokedex.data.remote.reponse

import com.google.gson.annotations.SerializedName

data class PokemonSpriteResponse(
    @SerializedName("other")
    val other: OtherSprites
)

data class OtherSprites(
    @SerializedName("official-artwork")
    val artwork: PokemonOfficialArtworkResponse
)

data class PokemonOfficialArtworkResponse(
    @SerializedName("front_default")
    val frontDefault: String,

    @SerializedName("front_shiny")
    val frontShiny: String
)
