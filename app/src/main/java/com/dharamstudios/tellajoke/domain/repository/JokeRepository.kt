package com.dharamstudios.tellajoke.domain.repository

import com.dharamstudios.tellajoke.domain.model.Joke

interface JokeRepository {

    suspend fun getJoke(url: String): Joke

}