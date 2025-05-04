package com.dharamstudios.tellajoke.data.mappers

import com.dharamstudios.tellajoke.data.model.JokeResponse
import com.dharamstudios.tellajoke.domain.model.Joke

fun JokeResponse.toJoke(): Joke {
    return Joke(
        category = this.category,
        type = this.type,
        joke = this.joke,
        setup = this.setup,
        delivery = this.delivery,
        id = this.id,
        safe = this.safe,
        lang = this.lang
    )
}