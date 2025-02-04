package com.study.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.study.pokedex.ui.theme.PokedexTheme

data class PokemonItemDetail(
    val name: String,
    val types: List<String>,
    val sprite: String,
    val color: Long = 0
)

val pokemonList: List<PokemonItemDetail> = listOf(
    PokemonItemDetail("Bulbassaur", listOf("glass", "poison"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png", 0xFF49d0b0),
    PokemonItemDetail("Ivysaur", listOf("glass", "poison"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png", 0xFF49d0b0),
    PokemonItemDetail("Venusaur", listOf("glass", "poison"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png", 0xFF49d0b0),
    PokemonItemDetail("Charmander", listOf("fire"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png", 0xffff6666),
    PokemonItemDetail("Charmeleon", listOf("fire"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/5.png", 0xffff6666),
    PokemonItemDetail("Charizard", listOf("fire", "flying"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/6.png", 0xffff6666),
    PokemonItemDetail("Squirtle", listOf("water"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png", 0xff66ccff),
    PokemonItemDetail("Wartortle", listOf("water"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/8.png", 0xff66ccff),
    PokemonItemDetail("Blastoise", listOf("water"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/9.png", 0xff66ccff),
    PokemonItemDetail("Pichu", listOf("electric"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/172.png", 0xffffd164),
    PokemonItemDetail("Pikachu", listOf("electric"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png", 0xffffd164),
    PokemonItemDetail("Raichu", listOf("electric"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/26.png", 0xffffd164),
    PokemonItemDetail("Caterpie", listOf("bug"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10.png", 0xFF49d0b0),
    PokemonItemDetail("Metapod", listOf("bug"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/11.png", 0xFF49d0b0),
    PokemonItemDetail("Butterfree", listOf("bug", "flying"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/12.png", 0xFF49d0b0),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                HomePage()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    Scaffold (
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent
                ),
                title = {
                    Text(
                        text = "Pokedex",
                        modifier = Modifier,
                        style = TextStyle(
                            fontSize = 23.sp
                        )
                    )
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            modifier = Modifier.size(80.dp),
                            contentDescription = "Localized description"
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
                x = 170.dp,
                y = (-165).dp
            )
        )
        HomeContent(
            Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun HomeContent(modifier: Modifier) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2)
    ) {
        items(pokemonList) { pokemon ->
            Box(modifier = Modifier
                .size(150.dp)
                .padding(5.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(Color(pokemon.color))
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_pokeball_line),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = Color(0x36FFFFFF)),
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
                            Box(modifier = Modifier.padding(0.dp, 3.dp)
                                .clip(RoundedCornerShape(30.dp))
                                .background(Color(0x42FFFFFF))
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
                    contentDescription = "Translated description of what the image contains",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 10.dp, bottom = 5.dp)
                        .align(alignment = Alignment.BottomEnd)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokedexTheme {
        HomePage()
    }
}