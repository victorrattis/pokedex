package com.study.pokedex.domain

data class PokemonDetail(
    val name: String,
    val types: List<String> = listOf(),
    val sprite: String = "",
)