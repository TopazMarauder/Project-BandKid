package com.bandkid.game.models

import com.bandkid.game.creatures.models.symphonists.Symphonist

data class Party(
    var orchestra: MutableList<Symphonist>,
    var currentSize: Int,
    var maxSize: Int
)
