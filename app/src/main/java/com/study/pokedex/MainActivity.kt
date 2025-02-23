package com.study.pokedex

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.study.pokedex.theme.PokedexTheme
import com.study.pokedex.ui.home.HomePage
import com.study.pokedex.ui.navigation.AppNavigation
import com.study.pokedex.ui.welcome.WelcomePage
import com.study.pokedex.ui.welcome.WelcomeViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity", "UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val navigation = AppNavigation()

        setContent {
            PokedexTheme {
                val currentPage by navigation.currentPage.collectAsState()
                when(currentPage) {
                    "home" ->  {
                        HomePage()
                    }
                    else -> {
                        val viewModel: WelcomeViewModel = viewModel(
                            factory = WelcomeViewModel.Factory(navigation)
                        )
                        WelcomePage(welcomeViewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokedexTheme {}
}