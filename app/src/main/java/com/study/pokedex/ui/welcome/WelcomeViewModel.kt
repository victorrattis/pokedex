package com.study.pokedex.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.study.pokedex.ui.navigation.AppNavigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomeViewModel(private val navigation: AppNavigation): ViewModel() {
    fun initialize() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(3000)
            navigation.nextHomePage()
        }
    }

    class Factory (private val navigation: AppNavigation) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WelcomeViewModel(navigation) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}