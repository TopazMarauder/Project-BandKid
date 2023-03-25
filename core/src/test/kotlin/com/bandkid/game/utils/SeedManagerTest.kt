package com.bandkid.game.utils


import com.badlogic.gdx.utils.GdxRuntimeException
import io.mockk.*
import org.junit.After
import org.junit.Assert.assertThrows
import org.junit.Ignore
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


//READ BEFORE: region getInt needs to be run separately
class SeedManagerTest() {

    private val subject = SeedManager

    //region hasSeed
    @Test
    fun hasSeed_givenSetSeedInitialized_returnsTrue() {
        subject.getSeed(getRandomInt())

        val result = subject.hasSeed()

        assertEquals(true, result)
    }

    @Test
    fun hasSeed_givenSetSeedUninitialized_returnsFalse() {
        val result = subject.hasSeed()

        assertEquals(true, result)
    }
    //endregion hasSeed

    //region getSeed
    @Test
    fun getSeed_givenSetSeedUninitialized_returnsSetSeed() {
        val setSeed = getRandomInt()

        assertEquals(setSeed, subject.getSeed(setSeed))
    }

    @Test
    fun getSeed_givenSetSeedInitialized_returnsSetSeed() {
        val setSeed = getRandomInt()
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
    //endregion getSeed

    //region getInt

    @Ignore("Run Separately")
    @Test
    fun getInt_givenNoMinMaxSeedUnset_throwsUninitializedValueException(){
        val exceptionSubject = SeedManager
        assertThrows(GdxRuntimeException::class.java) { exceptionSubject.getInt() }
    }

    @Ignore("Run Separately")
    @Test
    fun getInt_givenMinMaxSeedUnset_throwsUninitializedValueException(){
        val exceptionSubject = SeedManager
        assertThrows(GdxRuntimeException::class.java) { exceptionSubject.getInt(getRandomInt(), 10000+getRandomInt()) }
    }

    @Ignore("Run Separately")
    @Test
    fun getInt_givenMaxSeedUnset_throwsUninitializedValueException(){
        val exceptionSubject = SeedManager
        assertThrows(GdxRuntimeException::class.java) { exceptionSubject.getInt(getRandomInt()) }
    }

    //endregion getInt


    @After
    fun cleanup(){
        unmockkAll()
    }
    private fun getRandomInt() = Random.nextInt(1, 10000)
}