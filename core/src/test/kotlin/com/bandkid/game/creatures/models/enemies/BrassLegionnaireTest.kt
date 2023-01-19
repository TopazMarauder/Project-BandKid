package com.bandkid.game.creatures.models.enemies

import com.bandkid.game.battle.abilities.AbilityName
import com.bandkid.game.creatures.models.symphonists.Symphonist
import com.bandkid.game.utils.SeedManager
import io.mockk.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class BrassLegionnaireTest{

    private val subject = BrassLegionnaire()

    @Before
    fun setup() {
        SeedManager.getSeed()
    }

    @Test
    fun queueMove_setsBasicPhysicalAttackInQueue(){
        subject.queueMove(mutableListOf(mockk(), mockk()), mockk())

        assertEquals(AbilityName.BASIC_PHYSICAL_ATTACK, subject.moveInQueue?.second)
    }


    //Brass Legionnaire only has offensive moves
    @Test
    fun queueMove_setsRandomSymphonistAsTargetInQueue() {
        val symphonist1 =  object: Symphonist(1, 1, 1, 1, 1, 10, 10) {}
        val symphonist2 =  object: Symphonist(2, 1, 1, 1, 1, 10, 10) {}
        val symphonists = mutableListOf(symphonist1, symphonist2)
        val expected = Random.nextInt(0,2)

        mockkObject(SeedManager)
        every { SeedManager.getInt(0,2) } returns expected

        subject.queueMove(symphonists, mutableListOf())
        assertEquals(symphonists[expected], subject.moveInQueue?.first?.first())
    }


    @After
    fun cleanup(){
        unmockkAll()
    }

}