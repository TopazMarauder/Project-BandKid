package com.bandkid.game.models

import javax.inject.Singleton

@Singleton
data class Party(
    var orchestra: MutableList<Symphonist>,
    var currentSize: Int,
    var maxSize: Int
)
