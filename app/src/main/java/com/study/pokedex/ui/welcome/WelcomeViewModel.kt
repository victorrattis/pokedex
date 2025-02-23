package com.study.pokedex.ui.welcome

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WelcomeViewModel(private val navigation: NavController): ViewModel() {
    fun initialize() {
        viewModelScope.launch(Dispatchers.IO) {

            Log.d("devlog", "initialize")
            delay(3000)

            Log.d("devlog", "After 3 secunds")
            withContext(Dispatchers.Main) {
                Log.d("devlog", "Go to Home")
                navigation.navigate("detail/123") {
                    launchSingleTop = true
                }
            }
        }
    }

    class Factory (private val navigation: NavController) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WelcomeViewModel(navigation) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}