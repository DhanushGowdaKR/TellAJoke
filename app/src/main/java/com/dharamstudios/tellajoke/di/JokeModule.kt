package com.dharamstudios.tellajoke.di

import com.dharamstudios.tellajoke.data.remote.JokeApi
import com.dharamstudios.tellajoke.data.repository.JokeRepositoryImpl
import com.dharamstudios.tellajoke.domain.repository.JokeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JokeModule {

    @Provides
    @Singleton
    fun provideJokeApi(): JokeApi {
        return Retrofit.Builder()
            .baseUrl("https://v2.jokeapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<JokeApi>(JokeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideJokeRepository(api: JokeApi): JokeRepository {
        return JokeRepositoryImpl(api)
    }

}