package com.dharamstudios.tellajoke.presentation.homeScreen

sealed class HomeEvents {
    data class OnCategoryChange(val category: String) : HomeEvents()
    data class OnTypeChange(val type: String) : HomeEvents()
    data class OnBlacklistFlagsChange(val blacklistFlags: String) : HomeEvents()
    data class OnSafeModeChange(val safeMode: Boolean) : HomeEvents()

    data object OnGetJoke: HomeEvents()
}