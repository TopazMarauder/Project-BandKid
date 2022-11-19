package com.bandkid.game.player

import com.bandkid.game.models.Party
import com.bandkid.game.creatures.models.symphonists.Symphonist
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class PlayerProviderTest {

    private val subject = PlayerProvider(
        PlayerData(Party(mutableListOf(),0,1), mockk()))

    @Test
    fun getPartySize_returnsPairCurrentSizeMaxSize() {
        val expected = Pair(0, 1)
        val result = subject.getPartySize()
        assertEquals(expected, result)
    }

    @Test
    fun getOrchestra_returnsCorrectOrchestra() {
        val symphonist1 = Symphonist(1, 1,1,1,1,1,1)
        val symphonist2 = Symphonist(2, 1,1,1,1,1,1)
        val symphonist3 = Symphonist(3, 1,1,1,1,1,1)
        val orchestra1 = mutableListOf(symphonist1, symphonist2, symphonist3)
        val subject1 = PlayerProvider(PlayerData(Party(orchestra1,0,1), mockk()))

        assertEquals(orchestra1, subject1.getOrchestra())
    }

    @Test
    fun setOrchestra_returnsCorrectOrchestraWhenGetOrchestra() {
        val subject1 = PlayerProvider(PlayerData(Party(mutableListOf(),0,1), mockk()))
        val symphonist1 = Symphonist(1, 1,1,1,1,1,1)
        val symphonist2 = Symphonist(2, 1,1,1,1,1,1)
        val symphonist3 = Symphonist(3, 1,1,1,1,1,1)
        val orchestra1 = mutableListOf(symphonist1, symphonist2, symphonist3)

        subject1.setOrchestra(orchestra1)

        assertEquals(orchestra1, subject1.getOrchestra())
    }
}