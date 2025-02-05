package com.study.pokedex.ui.page.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.study.pokedex.R
import com.study.pokedex.ui.page.home.model.PokemonItemDetail

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