package com.bandkid.game.utils


import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.GdxRuntimeException
import com.bandkid.game.AsyncTest
import io.mockk.*
import org.junit.After
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Ignore
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


//READ BEFORE: region getInt needs to be run separately
class SeedManagerTest() {

    private val subject = SeedManager

    @Before
    fun clearManager() {
        subject.clearSeed()
    }

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

        assertEquals(false, result)
    }
    //endregion hasSeed

    //region clearSeed
    @Test
    fun clearSeed_setsSeedToNull() {
        subject.getSeed(getRandomInt())

        assertEquals(true, subject.hasSeed())

        subject.clearSeed()

        assertEquals(false, subject.hasSeed())
    }


    //endregion clearSeed

    //region getSeed
    @Test
    fun getSeed_givenSetSeedUninitialized_returnsSetSeed() {
        val setSeed = getRandomInt()

        assertEquals(setSeed, subject.getSeed(setSeed))
    }

    @Test
    fun getSeed_givenSetSeedInitialized_returnsSetSeed() {
        val setSeed = getRandomInt()
        subject.getSeed(setSeed)
        assertEquals(setSeed, subject.getSeed(getRandomInt()))
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