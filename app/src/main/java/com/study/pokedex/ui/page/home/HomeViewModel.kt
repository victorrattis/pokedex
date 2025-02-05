package com.study.pokedex.ui.page.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.pokedex.data.PokemonDetail
import com.study.pokedex.data.PokemonRepository
import com.study.pokedex.ui.page.home.model.PokemonItemDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    pokemonRepository: PokemonRepository
): ViewModel() {
    val uiState: StateFlow<HomeUiState> = pokemonRepository.pokemonList.map {
        it.map { toPokemonItemDetail(it) }
    }.map {
        HomeUiState.Loaded(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = HomeUiState.Loading,
        started = SharingStarted.Lazily
    )

    private val typeToColorMap = mapOf(
        "glass" to 0xFF49d0b0,
        "fire" to 0xffff6666,
        "water" to 0xff66ccff,
        "electric" to 0xffffd164,
        "bug" to 0xFF49d0b0
    )

    private fun toPokemonItemDetail(value: PokemonDetail) = PokemonItemDetail(
        value.name,
        value.types,
        value.sprite,
        typeToColorMap[value.types.first()] ?: 0
    )
}