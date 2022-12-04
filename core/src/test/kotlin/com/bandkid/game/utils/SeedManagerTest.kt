package com.bandkid.game.utils


import com.badlogic.gdx.utils.GdxRuntimeException
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert.assertThrows
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SeedManagerTest() {

    private val subject = SeedManager

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

//    @Test
//    fun getInt_givenNoMinMax_returnsRandomInt() {
//        val setSeed = subject.getSeed()
//        val randomNum = getRandomInt()
//        mockkConstructor(Random::class)
//        every { anyConstructed<Random>().nextInt() } returns randomNum
//
//        assertEquals(randomNum, subject.getInt())
//        unmockkAll()
//    }

    @Test
    fun getInt_givenNoMinMaxSeedUnset_throwsUninitializedValueException(){
        val exceptionSubject = SeedManager
        assertThrows(GdxRuntimeException::class.java) { exceptionSubject.getInt() }
    }

    @Test
    fun getInt_givenMinMaxSeedUnset_throwsUninitializedValueException(){
        val exceptionSubject = SeedManager
        assertThrows(GdxRuntimeException::class.java) { exceptionSubject.getInt(getRandomInt(), 10000+getRandomInt()) }
    }

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