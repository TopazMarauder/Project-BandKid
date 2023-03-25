package com.bandkid.game.player

import com.bandkid.game.player.models.Party
import com.bandkid.game.creatures.models.symphonists.Symphonist
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class PlayerProviderTest {

    private val subject = PlayerProvider


    @Test
    fun getPartySize_returnsPairCurrentSizeMaxSize() {
        val expected = Pair(0, 4)
        val result = subject.getPartySize()
        assertEquals(expected, result)
    }

    @Test
    fun getOrchestra_returnsCorrectOrchestra() {
        val subject1 = PlayerProvider
        val symphonist1 =  object: Symphonist(1, 1,1,1,1,1,1) {}
        val symphonist2 =  object: Symphonist(2, 1,1,1,1,1,1) {}
        val symphonist3 =  object: Symphonist(3, 1,1,1,1,1,1) {}
        val orchestra1 = mutableListOf(symphonist1, symphonist2, symphonist3)
        
        subject1.setOrchestra(orchestra1)

        assertEquals(orchestra1, subject1.getOrchestra())
    }

    @Test
    fun setOrchestra_returnsCorrectOrchestraWhenGetOrchestra() {
        val subject1 = PlayerProvider
        val symphonist1 =  object: Symphonist(1, 1,1,1,1,1,1) {}
        val symphonist2 =  object: Symphonist(2, 1,1,1,1,1,1) {}
        val symphonist3 =  object: Symphonist(3, 1,1,1,1,1,1) {}
        val orchestra1 = mutableListOf(symphonist1, symphonist2, symphonist3)

        subject1.setOrchestra(orchestra1)

        assertEquals(orchestra1, subject1.getOrchestra())
    }
}