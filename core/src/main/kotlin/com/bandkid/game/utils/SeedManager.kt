package com.bandkid.game.utils

import kotlin.random.Random

class SeedManager  {
    private var gameSeed: Int? = null

    fun getSeed(setSeed: Int? = null): Int =
        (setSeed ?: gameSeed ?: Random.nextInt()).also {gameSeed = it}


}