package com.study.pokedex.ui.home

import com.study.pokedex.ui.home.model.PokemonItemDetail

sealed interface HomeUiState {
    data object Loading: HomeUiState
    data class Success(val data: List<PokemonItemDetail>): HomeUiState
    data class Error(val message: String): HomeUiState
}