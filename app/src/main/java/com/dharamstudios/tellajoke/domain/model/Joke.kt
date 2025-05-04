package com.dharamstudios.tellajoke.domain.model

data class Joke(
    val category: String = "",
    val type: String = "",
    val joke: String? = null,
    val setup: String? = null,
    val delivery: String? = null,
    val id: Int = 0,
    val safe: Boolean = false,
    val lang: String = ""
)
