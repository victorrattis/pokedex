package com.study.pokedex.data

import com.study.pokedex.data.remote.IPokemonRemoteDataSource
import com.study.pokedex.data.remote.reponse.PokemonDetailResponse
import com.study.pokedex.domain.PokemonDetail
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val remoteDataSource: IPokemonRemoteDataSource
) {
    @OptIn(DelicateCoroutinesApi::class)
    fun getPokemonList(): StateFlow<List<PokemonDetail>> {
        return flow {
            emit(
                remoteDataSource.getPokemonList(12, 0)
                    .results.map { toPokemonDetail(remoteDataSource.getPokemonDetail(it.name)) }
            )
        }.stateIn(GlobalScope, SharingStarted.Eagerly, listOf())
    }

    private fun toPokemonDetail(value: PokemonDetailResponse): PokemonDetail {
        return PokemonDetail(
            name = value.name,
            sprite = value.sprites.other.artwork.frontDefault,
            types = value.types.map { it.type.name }
        )
    }

}
