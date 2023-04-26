package com.bandkid.game.battle

import com.bandkid.game.creatures.models.enemies.Enemy
import org.junit.Assert.assertEquals
import org.junit.Test

class EnemyProviderTest() {

    @Test
    fun getCacophony_returnsCorrectCacophony() {
        val subject1 = EnemyProvider
        val enemy1 =  object: Enemy(1, 1,1,1,1,1,1) {}
        val enemy2 =  object: Enemy(2, 1,1,1,1,1,1) {}
        val enemy3 =  object: Enemy(3, 1,1,1,1,1,1) {}
        val cacophony1 = mutableListOf(enemy1, enemy2, enemy3)

        subject1.setCacophony(cacophony1)

        assertEquals(cacophony1, subject1.getCacophony())
    }

    @Test
    fun setCacophony_returnsCorrectCacophonyWhenGetCacophony() {
        val subject1 = EnemyProvider
        val enemy1 =  object: Enemy(4, 1,1,1,1,1,1) {}
        val enemy2 =  object: Enemy(5, 1,1,1,1,1,1) {}
        val enemy3 =  object: Enemy(6, 1,1,1,1,1,1) {}
        val cacophony1 = mutableListOf(enemy1, enemy2, enemy3)

        subject1.setCacophony(cacophony1)

        assertEquals(cacophony1, subject1.getCacophony())
    }


    @Test
    fun setCacophony_returnsCorrectCacophony() {
        val subject1 = EnemyProvider
        val enemy1 =  object: Enemy(7, 1,1,1,1,1,1) {}
        val enemy2 =  object: Enemy(8, 1,1,1,1,1,1) {}
        val enemy3 =  object: Enemy(9, 1,1,1,1,1,1) {}
        val cacophony1 = mutableListOf(enemy1, enemy2, enemy3)

        assertEquals(cacophony1, subject1.setCacophony(cacophony1))
    }
}