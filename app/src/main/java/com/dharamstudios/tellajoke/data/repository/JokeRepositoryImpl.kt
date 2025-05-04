package com.dharamstudios.tellajoke.data.repository

import com.dharamstudios.tellajoke.data.mappers.toJoke
import com.dharamstudios.tellajoke.data.remote.JokeApi
import com.dharamstudios.tellajoke.domain.model.Joke
import com.dharamstudios.tellajoke.domain.repository.JokeRepository

class JokeRepositoryImpl(
    private val api: JokeApi
) : JokeRepository {
    override suspend fun getJoke(url: String): Joke {
        val response = api.getJoke(url)
        return response.toJoke()
    }
}