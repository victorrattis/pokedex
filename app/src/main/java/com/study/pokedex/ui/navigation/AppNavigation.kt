package com.study.pokedex.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.study.pokedex.ui.home.HomePage
import com.study.pokedex.ui.welcome.WelcomePage
import com.study.pokedex.ui.welcome.WelcomeViewModel


@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    Log.d("devlog", "AppNavigation")
    NavHost(navController = navController, startDestination = "home") {
        Log.d("devlog", "NavHost")

        composable("home") {
            Log.d("devlog", "composable home")
            HomePage()
        }

        composable("welcome") {
            Log.d("devlog", "composable Welcome")
            val viewModel: WelcomeViewModel = viewModel(
                factory = WelcomeViewModel.Factory(navController)
            )
            WelcomePage(welcomeViewModel = viewModel)
        }

        composable("detail/{Id}") { navBackStackEntry ->
            val uId = navBackStackEntry.arguments?.getString("Id")
            Log.d("devlog", "detail: $uId")
            HomePage()
        }
    }
}

