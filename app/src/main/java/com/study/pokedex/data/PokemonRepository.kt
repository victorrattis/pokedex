package com.study.pokedex.data

import com.study.pokedex.data.remote.IPokemonRemoteDataSource
import com.study.pokedex.data.remote.reponse.PokemonDetailResponse
import com.study.pokedex.domain.PokemonDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val remoteDataSource: IPokemonRemoteDataSource
) {
    fun getAllPokemonList(): Flow<List<PokemonDetail>> = flow {
        val pokemonList = remoteDataSource.getPokemonList(1304, 0).results.map {
            PokemonDetail(it.name, listOf(), "")
        }.toMutableList()
        emit(pokemonList)
        for (i in 0..10) {
            val detail = toPokemonDetail(remoteDataSource.getPokemonDetail(pokemonList[i].name))
            pokemonList[i] = pokemonList[i].copy(types = detail.types, sprite = detail.sprite)
            emit(pokemonList)
        }
    }

    private fun toPokemonDetail(value: PokemonDetailResponse) = PokemonDetail(
        name = value.name,
        sprite = value.sprites.other.artwork.frontDefault,
        types = value.types.sortedBy { it.slot }.map { it.type.name }
    )
}
