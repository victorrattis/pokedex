package com.study.pokedex.ui.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppNavigation {
    private val _currentPage: MutableStateFlow<String> = MutableStateFlow("")
    val currentPage: StateFlow<String> = _currentPage.asStateFlow()

    fun nextHomePage() {
        _currentPage.value = "home"
    }
}