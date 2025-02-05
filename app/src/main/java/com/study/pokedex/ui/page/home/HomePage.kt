package com.study.pokedex.ui.page.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.study.pokedex.R
import com.study.pokedex.ui.page.home.model.PokemonItemDetail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()) {
    Scaffold (
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent
                ),
                title = {
                    Text(
                        text = stringResource(R.string.pokedex_title),
                        modifier = Modifier,
                        style = TextStyle(
                            fontSize = 23.sp
                        )
                    )
                },
                actions = {
                    IconButton(onClick = {
                        // TODO: Implement the click action
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            modifier = Modifier.size(80.dp),
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Image(
            painter = painterResource(R.drawable.ic_pokeball_line),
            contentDescription = "",
            colorFilter = ColorFilter.tint(color = Color(0x18868686)),
            modifier = Modifier.offset(
                x = 177.dp,
                y = (-150).dp
            )
        )

        val uiState = viewModel.uiState.collectAsState().value
        when(uiState) {
            is HomeUiState.Loading -> {
                PokemonDataLoading()
            }
            is HomeUiState.Loaded -> {
                if (uiState.pokemonList.isEmpty()) {
                    EmptyContent(Modifier.padding(innerPadding))
                } else {
                    PokemonVerticalGrid(
                        uiState.pokemonList,
                        Modifier.padding(innerPadding)
                    )
                }
            }
            is HomeUiState.Error -> {
                // TODO: Implement UI Error
            }
        }
    }
}

@Composable
private fun PokemonVerticalGrid(
    pokemonList: List<PokemonItemDetail>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2)
    ) {
        items(pokemonList) { pokemon ->
            PokemonCard(
                pokemon,
                Modifier.padding(5.dp)
            )
        }
    }
}

@Composable
private fun PokemonDataLoading(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
            .wrapContentSize(Alignment.Center)

    ) {
        Text(
            text = "Loading...",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            style = typography.bodyLarge,
        )
    }
}

@Composable
private fun EmptyContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
            .wrapContentSize(Alignment.Center)

    ) {
        Text(
            text = "Empty Content",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            style = typography.bodyLarge,
        )
    }
}


