package com.bandkid.game.utils

import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SeedManagerTest() {

    private val subject = SeedManager

    @Test
    fun getSeed_givenSetSeedUninitialized_returnsSetSeed() {
        val setSeed = Random.nextInt()

        assertEquals(setSeed, subject.getSeed(setSeed))
    }

    @Test
    fun getSeed_givenSetSeedInitialized_returnsSetSeed() {
        val setSeed = Random.nextInt()
        subject.getSeed()
        assertEquals(setSeed, subject.getSeed(setSeed))
    }

    @Test
    fun getSeed_givenNoSetSeedUninitialized_returnsRandomSeed() {
        assertNotNull(subject.getSeed())
    }

    @Test
    fun getSeed_givenNoSeedInitialized_returnsPreviousSeed(){
        val randomSeed = subject.getSeed()
        assertEquals(randomSeed, subject.getSeed())
    }
}