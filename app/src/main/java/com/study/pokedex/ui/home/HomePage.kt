package com.study.pokedex.ui.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.study.pokedex.R
import com.study.pokedex.ui.home.components.PokemonVerticalGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(viewModel: HomeViewModel = hiltViewModel()) {
    Scaffold(
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

        val items = viewModel.pokemons.collectAsState(listOf())
        PokemonVerticalGrid(
            items,
            Modifier.padding(innerPadding)
        )

//        val items = viewModel.pokemonList.collectAsLazyPagingItems()
//        Log.d("devlog", "Refresh: ${items.loadState.refresh is LoadState.Loading}")
//
//        if (items.loadState.refresh is LoadState.Loading) {
//            PokemonDataLoading()
//        } else {
//            PokemonVerticalGrid(
//                items,
//                Modifier.padding(innerPadding)
//            )
//        }
    }
}

@Composable
private fun PokemonDataLoading(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
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
