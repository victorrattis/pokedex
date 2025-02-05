package com.study.pokedex.ui.page.home.model

data class PokemonItemDetail(
    val name: String,
    val types: List<String>,
    val sprite: String,
    val color: Long
)