package com.bandkid.game

import com.bandkid.game.activeabilities.AbilityEffectBundle
import com.bandkid.game.activeabilities.AbilityName.BASIC_PHYSICAL_ATTACK
import com.bandkid.game.activeabilities.ActiveAbilityManager
import com.bandkid.game.creatures.models.Enemy
import com.bandkid.game.models.Party
import com.bandkid.game.models.Symphonist
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Test

class ActionManagerTest {

    private val activeAbilityManger = mockk<ActiveAbilityManager>()
    private val subject = ActionManager(activeAbilityManger)
    init {
        subject.instanceParty = Party(mutableListOf(), 1, 1)
    }

    //region initiateOffensiveActiveAbility
    @Test
    fun initiateOffensiveActiveAbility_givenSingleEnemyTargetAsSymphonist_callsDoActiveAbilityOnTarget(){
        val testEnemy1 = mockk<Enemy>(relaxed = true)
        val testEnemy2 = mockk<Enemy>(relaxed = true)
        val symphonist = mockk<Symphonist>(relaxed = true)
        subject.instanceEnemies.add(testEnemy1)
        subject.instanceEnemies.add(testEnemy2)
        every { activeAbilityManger.doActiveAbility(any(),any(), any()) } returns mockk(relaxed = true)

        subject.initiateOffensiveActiveAbility(symphonist, listOf(1), BASIC_PHYSICAL_ATTACK)

        verify { activeAbilityManger.doActiveAbility(symphonist, testEnemy2, BASIC_PHYSICAL_ATTACK) }
    }

    @Test
    fun initiateOffensiveActiveAbility_givenSingleSymphonistTargetAsEnemy_callsDoActiveAbilityOnTarget(){
        val testEnemy1 = mockk<Enemy>(relaxed = true)
        val testEnemy2 = mockk<Enemy>(relaxed = true)
        val symphonist1 = mockk<Symphonist>(relaxed = true)
        val symphonist2 = mockk<Symphonist>(relaxed = true)
        subject.instanceEnemies.add(testEnemy1)
        subject.instanceEnemies.add(testEnemy2)
        subject.instanceParty.orchestra.add(symphonist1)
        subject.instanceParty.orchestra.add(symphonist2)
        every { activeAbilityManger.doActiveAbility(any(),any(), any()) } returns mockk(relaxed = true)

        subject.initiateOffensiveActiveAbility(testEnemy1, listOf(0), BASIC_PHYSICAL_ATTACK)

        verify { activeAbilityManger.doActiveAbility(testEnemy1, symphonist1, BASIC_PHYSICAL_ATTACK) }
    }

    @Test
    fun initiateOffensiveActiveAbility_givenEnemyTargetAsSymphonist_changesTargetEnemyStats() {
        val testEnemy1 = Enemy(1, 1, 1, 1, 1, 10, 10)
        val testEnemy2 = Enemy(1, 1, 1, 1, 1, 10, 10, isCrippled = false, isRaged = false)
        val symphonist = Symphonist(1, 1, 1, 1, 1, 10, 10)
        val testBundle = AbilityEffectBundle( damageDone = 5, healingDone = 10, shieldingDone = 10, crippleApplied = true, rageApplied = true)
        subject.instanceEnemies.add(testEnemy1)
        subject.instanceEnemies.add(testEnemy2)
        subject.instanceParty.orchestra.add(symphonist)
        every { activeAbilityManger.doActiveAbility(any(),any(), any()) } returns testBundle

        subject.initiateOffensiveActiveAbility(symphonist, listOf(1), BASIC_PHYSICAL_ATTACK)

        assertEquals(15, testEnemy2.currentHealthPoints)
        assertEquals(10, testEnemy2.shieldPoints)
        assertTrue(testEnemy2.isCrippled)
        assertTrue(testEnemy2.isRaged)


    }

    @Test
    fun initiateOffensiveActiveAbility_givenSymphonistTargetAsEnemy_changesTargetSymphonistStats() {
        val testEnemy1 = Enemy(1, 1, 1, 1, 1, 10, 10)
        val testEnemy2 = Enemy(1, 1, 1, 1, 1, 10, 10)
        val symphonist1 = Symphonist(1, 1, 1, 1, 1, 10, 10)
        val symphonist2 = Symphonist(1, 1, 1, 1, 1, 10, 10, isCrippled = true, isRaged = true)
        val testBundle = AbilityEffectBundle( damageDone = 5, healingDone = 10, shieldingDone = 10, crippleApplied = false, rageApplied = false)
        subject.instanceEnemies.add(testEnemy1)
        subject.instanceEnemies.add(testEnemy2)
        subject.instanceParty.orchestra.add(symphonist1)
        subject.instanceParty.orchestra.add(symphonist2)
        every { activeAbilityManger.doActiveAbility(any(),any(), any()) } returns testBundle

        subject.initiateOffensiveActiveAbility(testEnemy2, listOf(1), BASIC_PHYSICAL_ATTACK)

        assertEquals(15, symphonist2.currentHealthPoints)
        assertEquals(10, symphonist2.shieldPoints)
        assertFalse(symphonist2.isCrippled)
        assertFalse(symphonist2.isRaged)
    }

    @Test
    fun initiateOffensiveActiveAbility_givenLifestealAbilityAsSymphonist_healsSymphonist() {
        val testEnemy1 = Enemy(1, 1, 1, 1, 1, 10, 10)
        val symphonist = Symphonist(1, 1, 1, 1, 1, 10, 10)
        val testBundle = AbilityEffectBundle( lifestealDone = 10)
        subject.instanceEnemies.add(testEnemy1)
        subject.instanceParty.orchestra.add(symphonist)
        every { activeAbilityManger.doActiveAbility(any(),any(), any()) } returns testBundle

        subject.initiateOffensiveActiveAbility(symphonist, listOf(0), BASIC_PHYSICAL_ATTACK)

        assertEquals(20, symphonist.currentHealthPoints)
    }

    @Test
    fun initiateOffensiveActiveAbility_givenLifestealAbilityAsEnemy_healsEnemy() {
        val testEnemy1 = Enemy(1, 1, 1, 1, 1, 10, 10)
        val symphonist1 = Symphonist(1, 1, 1, 1, 1, 10, 10)
        val testBundle = AbilityEffectBundle( lifestealDone = 10)
        subject.instanceEnemies.add(testEnemy1)
        subject.instanceParty.orchestra.add(symphonist1)
        every { activeAbilityManger.doActiveAbility(any(),any(), any()) } returns testBundle

        subject.initiateOffensiveActiveAbility(testEnemy1, listOf(0), BASIC_PHYSICAL_ATTACK)

        assertEquals(20, testEnemy1.currentHealthPoints)
    }

    @Test
    fun initiateOffensiveActiveAbility_givenNonCripplingOrRagingAbilityAsSymphonist_doesNotChangeRagedOrCrippled() {
        val testEnemy1 = Enemy(1, 1, 1, 1, 1, 10, 10, isCrippled = true, isRaged = false)
        val symphonist = Symphonist(1, 1, 1, 1, 1, 10, 10)
        val testBundle = AbilityEffectBundle()
        subject.instanceEnemies.add(testEnemy1)
        subject.instanceParty.orchestra.add(symphonist)
        every { activeAbilityManger.doActiveAbility(any(),any(), any()) } returns testBundle

        subject.initiateOffensiveActiveAbility(symphonist, listOf(0), BASIC_PHYSICAL_ATTACK)

        assertTrue(testEnemy1.isCrippled)
        assertFalse(testEnemy1.isRaged)
    }
    //endregion initiateOffensiveAbility




}