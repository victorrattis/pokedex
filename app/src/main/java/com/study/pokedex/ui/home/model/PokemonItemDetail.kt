package com.study.pokedex.ui.home.model

data class PokemonItemDetail(
    val name: String,
    val types: List<String>,
    val sprite: String,
    val color: Long
)