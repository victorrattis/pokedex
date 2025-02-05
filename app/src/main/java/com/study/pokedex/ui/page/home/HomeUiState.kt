package com.study.pokedex.ui.page.home

import com.study.pokedex.ui.page.home.model.PokemonItemDetail


sealed interface HomeUiState {
    data object Loading: HomeUiState
    data class Loaded(val pokemonList: List<PokemonItemDetail>): HomeUiState
    data class Error(val message: String): HomeUiState
}
