package com.study.pokedex.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.pokedex.data.PokemonRepository
import com.study.pokedex.domain.PokemonDetail
import com.study.pokedex.ui.home.model.PokemonItemDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    pokemonRepository: PokemonRepository
): ViewModel() {
    val pokemonList: StateFlow<HomeUiState> = pokemonRepository.getPokemonList()
        .map { toPokemonList(it) }
        .filter { it.isNotEmpty() }
        .map { HomeUiState.Success(it) }
        .stateIn(viewModelScope, SharingStarted.Lazily, HomeUiState.Loading)

    // These colors was generated using AI tool
    private val typeToDarkColorMap = mapOf(
        "normal" to 0xFF8E8C67,   // Darker Beige
        "fighting" to 0xFF9B1E1E,  // Dark Red
        "flying" to 0xFF7A6FC2,    // Muted Purple-Blue
        "poison" to 0xFF813B82,    // Dark Purple
        "ground" to 0xFFB89550,    // Dark Brown-Yellow
        "rock" to 0xFF8E7B3F,      // Darker Brown
        "bug" to 0xFF7E8A19,       // Dark Green-Yellow
        "ghost" to 0xFF5D4774,     // Dark Purple
        "steel" to 0xFF8B8B9E,     // Dark Gray-Blue
        "fire" to 0xFFC0571F,      // Dark Orange
        "water" to 0xFF4668A3,     // Dark Blue
        "grass" to 0xFF5B8F3E,     // Dark Green
        "electric" to 0xFFB89B1E,  // Muted Yellow
        "psychic" to 0xFFC43D6A,   // Dark Pink-Red
        "ice" to 0xFF73A6A4,       // Muted Cyan
        "dragon" to 0xFF4B2599,    // Deep Purple
        "dark" to 0xFF50413F,      // Near Black
        "fairy" to 0xFFB26C8D,     // Muted Pink
        "stellar" to 0xFFCCAA22,   // Dimmed Gold (for Stellar Type)
        "unknown" to 0xFF526D6D    // Dark Teal
    )

    // This map of colors was generated using AI tool
    private val typeToColorMap = mapOf(
        "normal" to 0xFFA8A77A,
        "fighting" to 0xFFC22E28,
        "flying" to 0xFFA98FF3,
        "poison" to 0xFFA33EA1,
        "ground" to 0xFFE2BF65,
        "rock" to 0xFFB6A136,
        "bug" to 0xFFA6B91A,
        "ghost" to 0xFF735797,
        "steel" to 0xFFB7B7CE,
        "fire" to 0xFFEE8130,
        "water" to 0xFF6390F0,
        "grass" to 0xFF7AC74C,
        "electric" to 0xFFF7D02C,
        "psychic" to 0xFFF95587,
        "ice" to 0xFF96D9D6,
        "dragon" to 0xFF6F35FC,
        "dark" to 0xFF705746,
        "fairy" to 0xFFD685AD,
        "stellar" to 0xFFFFD700,
        "unknown" to 0xFF68A090
    )

    private fun toPokemonList(value: List<PokemonDetail>) = value.map { toPokemonItemDetail(it) }

    private fun toPokemonItemDetail(value: PokemonDetail) = PokemonItemDetail(
        value.name,
        value.types,
        value.sprite,
        typeToColorMap[value.types.first()] ?: 0xFFA8A77A
    )
}