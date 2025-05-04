package com.dharamstudios.tellajoke.data.remote

import com.dharamstudios.tellajoke.data.model.JokeResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface JokeApi {

    @GET
    suspend fun getJoke(@Url url: String): JokeResponse

}