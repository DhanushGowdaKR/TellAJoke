package com.dharamstudios.tellajoke.presentation.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dharamstudios.tellajoke.domain.repository.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.HttpUrl
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val jokeRepository: JokeRepository
) : ViewModel() {

    private val _homeState: MutableStateFlow<HomeState> = MutableStateFlow<HomeState>(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    fun event(event: HomeEvents) {
        when (event) {
            is HomeEvents.OnCategoryChange -> {
                val updated = homeState.value.category?.toMutableList() ?: mutableListOf()
                if (updated.contains(event.category)) updated.remove(event.category) else updated.add(
                    event.category
                )
                _homeState.update {
                    it.copy(
                        category = updated.toList()
                    )
                }
            }

            is HomeEvents.OnTypeChange -> {
                val updated = mutableListOf<String>()
//                updated.remove
//                if(updated.contains(event.type)) updated.remove(event.type) else updated.add(event.type)
                updated.add(event.type)
                Log.d("JOKE", updated.toString())
                _homeState.update {
                    it.copy(
                        type = updated.toList()
                    )
                }
            }

            is HomeEvents.OnSafeModeChange -> {
                _homeState.update {
                    it.copy(
                        safeMode = event.safeMode
                    )
                }
            }

            is HomeEvents.OnBlacklistFlagsChange -> {

                val updated = homeState.value.blacklistFlags?.toMutableList() ?: mutableListOf()
                if (updated.contains(event.blacklistFlags)) updated.remove(event.blacklistFlags) else updated.add(
                    event.blacklistFlags
                )

                _homeState.update {
                    it.copy(
                        blacklistFlags = updated.toList()
                    )
                }
            }

            is HomeEvents.OnGetJoke -> {
                val url = buildJokeApiUrl(
                    category = homeState.value.category ?: listOf("Any"),
                    type = homeState.value.type?.first() ?: "single",
                    blacklistFlags = homeState.value.blacklistFlags,
                    safeMode = homeState.value.safeMode
                )
                viewModelScope.launch {
                    val joke = jokeRepository.getJoke(url)
                    if(joke.joke == null) {
                        _homeState.update {
                            it.copy(
                                joke = "Set Up: ${joke.setup}\nDelivery: ${joke.delivery}"
                            )
                        }
                    } else if (joke.setup == null) {
                        _homeState.update {
                            it.copy(
                                joke = joke.joke
                            )
                        }
                    }
                }
            }
        }
    }

    fun buildJokeApiUrl(
        category: List<String> = listOf("Any"),
        type: String = "single",
        blacklistFlags: List<String>? = null,
        safeMode: Boolean = false
    ): String {
        val urlBuilder = HttpUrl.Builder()
            .scheme("https")
            .host("v2.jokeapi.dev")
            .addPathSegment("joke")
            .addPathSegment(category.joinToString(","))


        if (safeMode) urlBuilder.addQueryParameter("safe-mode", "true")
        blacklistFlags?.takeIf { it.isNotEmpty() }?.let {
            it.forEach {
                urlBuilder.addQueryParameter("blacklistFlags", it.trim().replace(" ", "").lowercase())
            }
        }
        type.let { urlBuilder.addQueryParameter("type", it.trim().replace(" ", "").lowercase()) }
        Log.d("JOKE", urlBuilder.build().toString())
        return urlBuilder.build().toString()
    }

}