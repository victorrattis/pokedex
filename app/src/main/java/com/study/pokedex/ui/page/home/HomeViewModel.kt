package com.study.pokedex.ui.page.home

import androidx.lifecycle.ViewModel
import com.study.pokedex.data.PokemonRepository
import com.study.pokedex.ui.page.home.model.PokemonItemDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    pokemonRepository: PokemonRepository
): ViewModel() {
    val pokemons: List<PokemonItemDetail> = pokemonRepository.getPokemonList().map {
        PokemonItemDetail(
            it.name,
            it.types,
            it.sprite,
            typeToColorMap[it.types.first()] ?: 0
        )
    }

    private val typeToColorMap = mapOf(
        "glass" to 0xFF49d0b0,
        "fire" to 0xffff6666,
        "water" to 0xff66ccff,
        "electric" to 0xffffd164,
        "bug" to 0xFF49d0b0
    )
}