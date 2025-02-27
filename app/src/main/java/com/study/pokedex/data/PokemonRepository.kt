package com.study.pokedex.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.study.pokedex.data.remote.IPokemonRemoteDataSource
import com.study.pokedex.data.remote.reponse.PokemonDetailResponse
import com.study.pokedex.domain.PokemonDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
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

    fun getPokemonList(): Flow<PagingData<PokemonDetail>> {
        return Pager(PagingConfig(pageSize = 12, enablePlaceholders = false)) {
            PokemonPagingSource { limit, offset ->
                remoteDataSource.getPokemonList(limit, offset)
                    .results
                    .map { toPokemonDetail(remoteDataSource.getPokemonDetail(it.name)) }
            }
        }.flow
    }

    private fun toPokemonDetail(value: PokemonDetailResponse) = PokemonDetail(
        name = value.name,
        sprite = value.sprites.other.artwork.frontDefault,
        types = value.types.sortedBy { it.slot }.map { it.type.name }
    )
}

class PokemonPagingSource(
    private val dataLoader: suspend (limit: Int, offset: Int) -> List<PokemonDetail>
): PagingSource<Int, PokemonDetail>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, PokemonDetail> = withContext(Dispatchers.Main) {
        try {
            val currentPage = params.key ?: 0
            val data = dataLoader(params.loadSize, currentPage * params.loadSize)
            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (currentPage < data.size) currentPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, PokemonDetail>
    ): Int? = state.anchorPosition
}
