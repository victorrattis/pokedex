package com.study.pokedex.ui.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.study.pokedex.ui.home.model.PokemonItemDetail

@Composable
fun PokemonVerticalGrid(
    pokemonList: State<List<PokemonItemDetail>>,
    modifier: Modifier = Modifier
) {
    val n = if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(n)
    ) {
        items(pokemonList.value.size, key = { pokemonList.value[it].name }) { index ->
            PokemonCard(
                pokemonList.value[index],
                Modifier.padding(5.dp)
            )
        }
    }
}