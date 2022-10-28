package com.bandkid.game.models

data class Party(
    var orchestra: List<Symphonist>,
    var currentSize: Int,
    var maxSize: Int
)
