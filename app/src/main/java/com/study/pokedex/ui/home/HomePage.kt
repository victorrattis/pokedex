package com.study.pokedex.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.study.pokedex.R
import com.study.pokedex.ui.home.components.InLineSearchBar
import com.study.pokedex.ui.home.components.LoadingPage
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
                    Row {
//                        Text(
//                            text = stringResource(R.string.pokedex_title),
//                            modifier = Modifier,
//                            style = TextStyle(
//                                fontSize = 23.sp
//                            )
//                        )

                        InLineSearchBar(viewModel.searchQuery.collectAsState()) {
                            viewModel.onSearchTextChanged(it)
                        }
                    }

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
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = Color(0x18868686)),
            modifier = Modifier.offset(
                x = 177.dp,
                y = (-150).dp
            )
        )

        val uiState = viewModel.pageState.collectAsState()
        when (uiState.value) {
            is HomeUiState.Loading -> {
                LoadingPage()
            }
            is HomeUiState.Success -> {
                PokemonVerticalGrid(
                    (uiState.value as HomeUiState.Success).data,
                    Modifier.padding(innerPadding)
                )
            }
            is HomeUiState.Error -> {}
        }
    }
}
