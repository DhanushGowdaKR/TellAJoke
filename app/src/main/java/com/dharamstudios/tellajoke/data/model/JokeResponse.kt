package com.dharamstudios.tellajoke.data.model

data class JokeResponse(
    val error: Boolean,
    val category: String,
    val type: String,
    val joke: String?,
    val setup: String?,
    val delivery: String?,
    val flags: Flags,
    val id: Int,
    val safe: Boolean,
    val lang: String
)
