package com.study.pokedex.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.study.pokedex.data.PokemonFilter

@Composable
fun InLineSearchBar(
    searchQuery: State<PokemonFilter?>,
    onSearchTextChanged: (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    if (isExpanded) {
        TextField(
            value = searchQuery.value?.name ?: "",
            onValueChange = onSearchTextChanged,
            placeholder = { Text("Search...", color = Color.Gray) },
            leadingIcon = {
                IconButton(onClick = { isExpanded = false; onSearchTextChanged("") }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Close Search")
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    } else {
        IconButton(
            onClick = { isExpanded = true },
            modifier = Modifier
                .size(48.dp)
                .background(Color.Transparent, shape = CircleShape) // Background when collapsed
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        }
    }
}