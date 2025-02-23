package com.study.pokedex.ui.welcome

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WelcomePage(
    modifier: Modifier = Modifier,
    welcomeViewModel: WelcomeViewModel
) {
    Scaffold {
        welcomeViewModel.initialize()
        Column(
            modifier = modifier.fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = "Welcome",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                style = typography.bodyLarge,
            )
        }
    }
}