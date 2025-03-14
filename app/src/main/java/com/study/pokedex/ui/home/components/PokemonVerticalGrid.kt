package com.study.pokedex.ui.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.study.pokedex.ui.home.model.PokemonItemDetail

@Composable
fun PokemonVerticalGrid(
    pokemonList: List<PokemonItemDetail>,
    modifier: Modifier = Modifier
) {
    val columnNumber = if (isPortraitOrientation()) 2 else 4
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(columnNumber)
    ) {
        items(pokemonList.size, key = { pokemonList[it].name }) { index ->
            PokemonCard(
                pokemonList[index],
                Modifier.padding(5.dp)
            )
        }
    }
}

@Composable
private fun isPortraitOrientation(): Boolean =
    LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT