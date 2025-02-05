package com.study.pokedex.data

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonRepository @Inject constructor() {
    private val fakeData: List<PokemonDetail> = listOf(
        PokemonDetail("Bulbassaur", listOf("glass", "poison"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
        PokemonDetail("Ivysaur", listOf("glass", "poison"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png"),
        PokemonDetail("Venusaur", listOf("glass", "poison"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png"),
        PokemonDetail("Charmander", listOf("fire"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"),
        PokemonDetail("Charmeleon", listOf("fire"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/5.png"),
        PokemonDetail("Charizard", listOf("fire", "flying"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/6.png"),
        PokemonDetail("Squirtle", listOf("water"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png"),
        PokemonDetail("Wartortle", listOf("water"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/8.png"),
        PokemonDetail("Blastoise", listOf("water"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/9.png"),
        PokemonDetail("Pichu", listOf("electric"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/172.png"),
        PokemonDetail("Pikachu", listOf("electric"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png"),
        PokemonDetail("Raichu", listOf("electric"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/26.png"),
        PokemonDetail("Caterpie", listOf("bug"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10.png"),
        PokemonDetail("Metapod", listOf("bug"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/11.png"),
        PokemonDetail("Butterfree", listOf("bug", "flying"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/12.png"),
    )

    val pokemonList: Flow<List<PokemonDetail>> = channelFlow {
        // TODO: This should be removed when adding Retrofit
        GlobalScope.launch {
            delay(5000)
            send(fakeData)
        }
        awaitClose()
    }
}