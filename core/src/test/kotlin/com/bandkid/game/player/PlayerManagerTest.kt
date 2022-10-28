package com.bandkid.game.player

import com.bandkid.game.models.Coordinates
import com.bandkid.game.models.Party
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class PlayerManagerTest {
    private val subject = PlayerManager(
        PlayerData(Party(emptyList(),0,1), mockk()))

    @Test
    fun getPartySize_returnsPairCurrentSizeMaxSize() {
        val expected = Pair(0, 1)
        val result = subject.getPartySize()
        assertEquals(expected, result)
    }
}