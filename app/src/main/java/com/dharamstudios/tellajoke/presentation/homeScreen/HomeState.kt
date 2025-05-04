package com.dharamstudios.tellajoke.presentation.homeScreen

data class HomeState(
    val joke: String? = null,
    val isLoading: Boolean = false,
    val category: List<String>? = null,
    val type: List<String>? = null,
    val blacklistFlags: List<String>? = null,
    val safeMode: Boolean = false
)