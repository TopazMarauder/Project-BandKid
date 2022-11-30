package com.bandkid.game.creatures.models.enemies

import com.bandkid.game.battle.activeabilities.AbilityName
import com.bandkid.game.creatures.models.symphonists.Symphonist
import com.bandkid.game.utils.SeedManager
import io.mockk.*
import org.junit.Assert.*
import org.junit.Test
import kotlin.random.Random

class BrassLegionnaireTest{

    private val subject = BrassLegionnaire()

    @Test
    fun queueMove_setsBasicPhysicalAttackInQueue(){
        subject.queueMove(mutableListOf(mockk(), mockk()), mockk())

        assertEquals(AbilityName.BASIC_PHYSICAL_ATTACK, subject.moveInQueue?.second)
    }


    //Brass Legionnaire only has offensive moves
    //TODO figure out how to get the random test working
//    @Test
//    fun queueMove_setsRandomSymphonistAsTargetInQueueA() {
//        val symphonist1 = Symphonist(1, 1, 1, 1, 1, 10, 10)
//        val symphonist2 = Symphonist(2, 1, 1, 1, 1, 10, 10)
//        val symphonists = mutableListOf(symphonist1, symphonist2)
////        val choice = Random.nextInt(0,2)
////        every { Random(mockk()).nextInt(0, 2) } returns choice
//
//        subject.queueMove(symphonists, mutableListOf())
//
//        assertEquals(symphonists[0], subject.moveInQueue?.first)
//    }

    @Test
    fun queueMove_setsRandomSymphonistAsTargetInQueueB() {
        val symphonist1 = Symphonist(1, 1, 1, 1, 1, 10, 10)
        val symphonist2 = Symphonist(2, 1, 1, 1, 1, 10, 10)
        val symphonists = mutableListOf(symphonist1, symphonist2)
//        val choice = Random.nextInt(0,2)
//        every { Random(mockk()).nextInt(0, 2) } returns choice

        subject.queueMove(symphonists, mutableListOf())

        assertEquals(symphonists[1], subject.moveInQueue?.first)
    }



}