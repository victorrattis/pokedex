package com.study.pokedex.ui.home

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.study.pokedex.R
import com.study.pokedex.theme.PokedexTheme
import kotlin.concurrent.thread

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    PokedexTheme {
        Log.d("devlog", "HomePage")
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

            var isLoading by remember { mutableStateOf(true) }
            val (pokemonList, setValue) = remember { mutableStateOf<List<PokemonItemDetail>>(listOf()) }
            thread {
                Thread.sleep(2000)
                setValue(fakeData.map { toPokemonItemDetail(it) })
                isLoading = false
            }

            if (isLoading) {
                PokemonDataLoading()
            } else {
                if (pokemonList.isEmpty()) {
                    EmptyContent()
                } else {
                    PokemonVerticalGrid(
                        pokemonList,
                        Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

private val typeToColorMap = mapOf(
    "glass" to 0xFF49d0b0,
    "fire" to 0xffff6666,
    "water" to 0xff66ccff,
    "electric" to 0xffffd164,
    "bug" to 0xFF49d0b0
)

private fun toPokemonItemDetail(value: PokemonDetail) = PokemonItemDetail(
    value.name,
    value.types,
    value.sprite,
    typeToColorMap[value.types.first()] ?: 0
)

@Composable
private fun PokemonVerticalGrid(
    pokemonList: List<PokemonItemDetail>,
    modifier: Modifier = Modifier
) {
    val n = if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(n)
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

@Composable
fun PokemonCard(
    pokemon: PokemonItemDetail,
    modifier: Modifier = Modifier,
    highlightColor: Color = Color(0x42FFFFFF)
) {
    Box(modifier = modifier
        .size(150.dp)
        .clip(RoundedCornerShape(30.dp))
        .background(Color(pokemon.color))
    ) {
        Image(
            painter = painterResource(R.drawable.ic_pokeball_line),
            contentDescription = "",
            colorFilter = ColorFilter.tint(color = highlightColor),
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .offset(x = 50.dp, y = 50.dp)
        )

        Column(
            modifier = Modifier.padding(
                top = 32.dp,
                start = 21.dp
            )
        ) {
            Text(
                text = pokemon.name,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                )
            )
            LazyColumn(
                contentPadding = PaddingValues(top = 5.dp, start = 5.dp)
            ) {
                items(pokemon.types) { type ->
                    Box(modifier = Modifier
                        .padding(0.dp, 3.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(highlightColor)
                    ) {
                        Text(
                            modifier = Modifier.padding(10.dp, 4.dp),
                            text = type,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        )
                    }
                }
            }
        }

        AsyncImage(
            model = pokemon.sprite,
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .size(100.dp)
                .padding(end = 10.dp, bottom = 5.dp)
                .align(alignment = Alignment.BottomEnd)
        )
    }
}

data class PokemonDetail(
    val name: String,
    val types: List<String>,
    val sprite: String,
)

data class PokemonItemDetail(
    val name: String,
    val types: List<String>,
    val sprite: String,
    val color: Long
)