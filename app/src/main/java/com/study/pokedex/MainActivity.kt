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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.study.pokedex.ui.theme.PokedexTheme

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
        items(22) { item ->
            Box(modifier = Modifier
                .size(150.dp)
                .padding(5.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(Color(0xFF49d0b0))
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
                        text = "Bulbassaur",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    )
                    LazyColumn(contentPadding = PaddingValues(top = 5.dp, start = 5.dp  )) {
                        items(2) {
                            Box(modifier = Modifier.padding(0.dp, 3.dp)
                                .clip(RoundedCornerShape(30.dp))
                                .background(Color(0xFF62e1c6))
                            ) {
                                Text(
                                    modifier = Modifier.padding(10.dp, 4.dp),
                                    text = "Glass",
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
                    model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                    contentDescription = "Translated description of what the image contains",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 10.dp, bottom = 10.dp)
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