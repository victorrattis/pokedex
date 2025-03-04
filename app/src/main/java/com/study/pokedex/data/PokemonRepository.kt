package com.study.pokedex.data

import android.util.Log
import com.study.pokedex.data.local.PokemonEntity
import com.study.pokedex.data.remote.reponse.PokemonDetailResponse
import com.study.pokedex.domain.PokemonDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

data class PokemonFilter(
    val name: String? = null,
    val type: String? = null
) {
    fun isEmpty(): Boolean = name.isNullOrEmpty() && type.isNullOrEmpty()
}

class PokemonRepository(
    private val remoteDataSource: IPokemonRemoteDataSource,
    private val localDataSource: IPokemonLocalDataSource,
    private val repositoryScope: CoroutineScope
) {
    private var isLoading: Boolean = false

    fun getAllPokemonList(
        filter: PokemonFilter? = null
    ): Flow<List<PokemonDetail>> = localDataSource.getAllPokemon()
        .also { fetchData(it) }
        .filterNotNull()
        .filter { it.isNotEmpty() }
        .map { filterBy(it, filter) }
        .map { toPokemonDetailList(it) }

    private fun filterBy(
        pokemonList: List<PokemonEntity>, filter: PokemonFilter?
    ): List<PokemonEntity> = if (filter != null) pokemonList.filter {
        (filter.name.isNullOrEmpty() || it.name.startsWith(filter.name)) &&
        (filter.type.isNullOrEmpty() || it.types?.contains(filter.type) == true)
    } else pokemonList

    private fun fetchData(data: Flow<List<PokemonEntity>?>) {
        Log.d("devlog", "fetchData")
        repositoryScope.launch(Dispatchers.IO) {
            data.collectLatest {
                if (!isLoading) {
                    Log.d("devlog", "fetchData: ${it?.size}")
                    if (it?.isEmpty() == true) {
                        loadPokemonListRemotely()
                    } else if (!it.isNullOrEmpty()) {
                        isLoading = true
                        val count = it.filter { !it.isLoaded }.size
                        Log.d("devlog", "loadUnLoadedPokemonDetail: $count")
                        loadUnLoadedPokemonDetail(it)
                    }
                }
            }
        }
    }

    private suspend fun loadPokemonListRemotely() {
        Log.d("devlog", "loadPokemonListRemotely")
        // First, send a request to know the number of Pokemon
        val response = remoteDataSource.getPokemonList(1, 0)
        remoteDataSource.getPokemonList(response.count, 0)
            .results
            .map { PokemonEntity(name = it.name, detailUrl = it.url) }
            .let { localDataSource.insertPokemon(*it.toTypedArray()) }
    }

    private fun loadUnLoadedPokemonDetail(data: List<PokemonEntity>) {
        repositoryScope.launch(Dispatchers.IO) {
            data.filter { !it.isLoaded }
                .forEach {
                    Log.d("devlog", "getAllPokemon.detail: ${it.name}")
                    val detail = remoteDataSource.getPokemonDetail(it.name)
                    it.copy(
                        types = detail.types.sortedBy { it.slot }.map { it.type.name },
                        sprite = detail.sprites.other.artwork.frontDefault,
                        order = detail.order,
                        isLoaded = true
                    ).let {
                        localDataSource.updatePokemon(it)
                    }
                    Log.d("devlog", "getAllPokemon.detail end - ${it.name}")
                }
        }
    }

    private fun toPokemonDetailList(value: List<PokemonEntity>) = value.map {
        PokemonDetail(it.name, it.types ?: listOf(), it.sprite ?: "")
    }

    private fun toPokemonDetail(value: PokemonDetailResponse) = PokemonDetail(
        name = value.name,
        sprite = value.sprites.other.artwork.frontDefault,
        types = value.types.sortedBy { it.slot }.map { it.type.name }
    )
}
