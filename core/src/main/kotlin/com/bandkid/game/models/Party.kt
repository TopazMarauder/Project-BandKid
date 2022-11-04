package com.bandkid.game.models

data class Party(
    var orchestra: MutableList<Symphonist>,
    var currentSize: Int,
    var maxSize: Int
)
