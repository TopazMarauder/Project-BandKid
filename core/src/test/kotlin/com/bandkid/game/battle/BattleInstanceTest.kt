package com.bandkid.game.battle

import io.mockk.verify
import org.junit.Test

class BattleInstanceTest {

    private val subject = BattleInstance()

    @Test
    fun onCreate_callsOnPassivePhase(){
        subject.onCreate()

        }

}