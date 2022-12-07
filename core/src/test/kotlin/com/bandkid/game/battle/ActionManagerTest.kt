package com.bandkid.game.battle

import com.bandkid.game.battle.activeabilities.AbilityEffectBundle
import com.bandkid.game.battle.activeabilities.AbilityName.BASIC_PHYSICAL_ATTACK
import com.bandkid.game.battle.activeabilities.ActiveAbilityManager
import com.bandkid.game.creatures.models.enemies.Enemy
import com.bandkid.game.models.Party
import com.bandkid.game.creatures.models.symphonists.Symphonist
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

    //region initiateActiveAbility
    @Test
    fun initiateActiveAbility_givenSingleEnemyTargetAsSymphonist_callsDoActiveAbilityOnTarget(){
        val testEnemy1 = mockk<Enemy>(relaxed = true)
        val testEnemy2 = mockk<Enemy>(relaxed = true)
        val symphonist = mockk<Symphonist>(relaxed = true)
        subject.instanceEnemies.add(testEnemy1)
        subject.instanceEnemies.add(testEnemy2)
        every { activeAbilityManger.doActiveAbility(any(),any(), any()) } returns mockk(relaxed = true)

        subject.initiateActiveAbility(symphonist, BASIC_PHYSICAL_ATTACK, testEnemy2)

        verify { activeAbilityManger.doActiveAbility(symphonist, testEnemy2, BASIC_PHYSICAL_ATTACK) }
    }

    @Test
    fun initiateActiveAbility_givenMultipleEnemyTargetAsSymphonist_callsDoActiveAbilityOnTargets(){
        val testEnemy1 = mockk<Enemy>(relaxed = true)
        val testEnemy2 = mockk<Enemy>(relaxed = true)
        val symphonist = mockk<Symphonist>(relaxed = true)
        subject.instanceEnemies.add(testEnemy1)
        subject.instanceEnemies.add(testEnemy2)
        every { activeAbilityManger.doActiveAbility(any(),any(), any()) } returns mockk(relaxed = true)

        subject.initiateActiveAbility(symphonist, BASIC_PHYSICAL_ATTACK, testEnemy1, testEnemy2)

        verify { activeAbilityManger.doActiveAbility(symphonist, testEnemy1, BASIC_PHYSICAL_ATTACK) }
        verify { activeAbilityManger.doActiveAbility(symphonist, testEnemy2, BASIC_PHYSICAL_ATTACK) }
    }


    @Test
    fun initiateActiveAbility_givenSingleSymphonistTargetAsEnemy_callsDoActiveAbilityOnTarget(){
        val testEnemy1 = mockk<Enemy>(relaxed = true)
        val testEnemy2 = mockk<Enemy>(relaxed = true)
        val symphonist1 = mockk<Symphonist>(relaxed = true)
        val symphonist2 = mockk<Symphonist>(relaxed = true)
        subject.instanceEnemies.add(testEnemy1)
        subject.instanceEnemies.add(testEnemy2)
        subject.instanceParty.orchestra.add(symphonist1)
        subject.instanceParty.orchestra.add(symphonist2)
        every { activeAbilityManger.doActiveAbility(any(),any(), any()) } returns mockk(relaxed = true)

        subject.initiateActiveAbility(testEnemy1,  BASIC_PHYSICAL_ATTACK, symphonist1)

        verify { activeAbilityManger.doActiveAbility(testEnemy1, symphonist1, BASIC_PHYSICAL_ATTACK) }
    }

    @Test
    fun initiateActiveAbility_givenMultipleSymphonistTargetAsEnemy_callsDoActiveAbilityOnTargets(){
        val testEnemy1 = mockk<Enemy>(relaxed = true)
        val testEnemy2 = mockk<Enemy>(relaxed = true)
        val symphonist1 = mockk<Symphonist>(relaxed = true)
        val symphonist2 = mockk<Symphonist>(relaxed = true)
        subject.instanceEnemies.add(testEnemy1)
        subject.instanceEnemies.add(testEnemy2)
        subject.instanceParty.orchestra.add(symphonist1)
        subject.instanceParty.orchestra.add(symphonist2)
        every { activeAbilityManger.doActiveAbility(any(),any(), any()) } returns mockk(relaxed = true)

        subject.initiateActiveAbility(testEnemy1,  BASIC_PHYSICAL_ATTACK, symphonist1, symphonist2)

        verify { activeAbilityManger.doActiveAbility(testEnemy1, symphonist1, BASIC_PHYSICAL_ATTACK) }
        verify { activeAbilityManger.doActiveAbility(testEnemy1, symphonist2, BASIC_PHYSICAL_ATTACK) }
    }

    @Test
    fun initiateActiveAbility_givenEnemyTargetAsSymphonist_changesTargetEnemyStats() {
        val testEnemy1 = object: Enemy(1, 1, 1, 1, 1, 10, 10) {}
        val testEnemy2 = object: Enemy(1, 1, 1, 1, 1, 10, 10, isCrippled = false, isRaged = false) {}
        val symphonist = Symphonist(1, 1, 1, 1, 1, 10, 10)
        val testBundle = AbilityEffectBundle( damageDone = 5, healingDone = 10, shieldingDone = 10, crippleApplied = true, rageApplied = true)
        subject.instanceEnemies.add(testEnemy1)
        subject.instanceEnemies.add(testEnemy2)
        subject.instanceParty.orchestra.add(symphonist)
        every { activeAbilityManger.doActiveAbility(any(),any(), any()) } returns testBundle

        subject.initiateActiveAbility(symphonist, BASIC_PHYSICAL_ATTACK, testEnemy2)

        assertEquals(15, testEnemy2.currentHealthPoints)
        assertEquals(10, testEnemy2.shieldPoints)
        assertTrue(testEnemy2.isCrippled)
        assertTrue(testEnemy2.isRaged)
    }

    @Test
    fun initiateActiveAbility_givenMultipleEnemyTargetsAsSymphonist_changesTargetEnemiesStats() {
        val testEnemy1 = object: Enemy(1, 1, 1, 1, 1, 10, 10) {}
        val testEnemy2 = object: Enemy(1, 1, 1, 1, 1, 10, 10, isCrippled = false, isRaged = false) {}
        val symphonist = Symphonist(1, 1, 1, 1, 1, 10, 10)
        val testBundle = AbilityEffectBundle( damageDone = 5, healingDone = 10, shieldingDone = 10, crippleApplied = true, rageApplied = true)
        subject.instanceEnemies.add(testEnemy1)
        subject.instanceEnemies.add(testEnemy2)
        subject.instanceParty.orchestra.add(symphonist)
        every { activeAbilityManger.doActiveAbility(any(),any(), any()) } returns testBundle

        subject.initiateActiveAbility(symphonist, BASIC_PHYSICAL_ATTACK, testEnemy1, testEnemy2)

        assertEquals(15, testEnemy1.currentHealthPoints)
        assertEquals(10, testEnemy1.shieldPoints)
        assertTrue(testEnemy1.isCrippled)
        assertTrue(testEnemy1.isRaged)
        assertEquals(15, testEnemy2.currentHealthPoints)
        assertEquals(10, testEnemy2.shieldPoints)
        assertTrue(testEnemy2.isCrippled)
        assertTrue(testEnemy2.isRaged)
    }

    @Test
    fun initiateActiveAbility_givenSymphonistTargetAsEnemy_changesTargetSymphonistStats() {
        val testEnemy1 = object: Enemy(1, 1, 1, 1, 1, 10, 10) {}
        val testEnemy2 = object: Enemy(1, 1, 1, 1, 1, 10, 10) {}
        val symphonist1 = Symphonist(1, 1, 1, 1, 1, 10, 10)
        val symphonist2 = Symphonist(1, 1, 1, 1, 1, 10, 10, isCrippled = true, isRaged = true)
        val testBundle = AbilityEffectBundle( damageDone = 5, healingDone = 10, shieldingDone = 10, crippleApplied = false, rageApplied = false)
        subject.instanceEnemies.add(testEnemy1)
        subject.instanceEnemies.add(testEnemy2)
        subject.instanceParty.orchestra.add(symphonist1)
        subject.instanceParty.orchestra.add(symphonist2)
        every { activeAbilityManger.doActiveAbility(any(),any(), any()) } returns testBundle

        subject.initiateActiveAbility(testEnemy2, BASIC_PHYSICAL_ATTACK, symphonist2)

        assertEquals(15, symphonist2.currentHealthPoints)
        assertEquals(10, symphonist2.shieldPoints)
        assertFalse(symphonist2.isCrippled)
        assertFalse(symphonist2.isRaged)
    }

    @Test
    fun initiateActiveAbility_givenMultipleSymphonistTargetsAsEnemy_changesTargetSymphonistsStats() {
        val testEnemy1 = object: Enemy(1, 1, 1, 1, 1, 10, 10) {}
        val testEnemy2 = object: Enemy(1, 1, 1, 1, 1, 10, 10) {}
        val symphonist1 = Symphonist(1, 1, 1, 1, 1, 10, 10)
        val symphonist2 = Symphonist(1, 1, 1, 1, 1, 10, 10, isCrippled = true, isRaged = true)
        val testBundle = AbilityEffectBundle( damageDone = 5, healingDone = 10, shieldingDone = 10, crippleApplied = false, rageApplied = false)
        subject.instanceEnemies.add(testEnemy1)
        subject.instanceEnemies.add(testEnemy2)
        subject.instanceParty.orchestra.add(symphonist1)
        subject.instanceParty.orchestra.add(symphonist2)
        every { activeAbilityManger.doActiveAbility(any(),any(), any()) } returns testBundle

        subject.initiateActiveAbility(testEnemy2, BASIC_PHYSICAL_ATTACK, symphonist1, symphonist2)

        assertEquals(15, symphonist1.currentHealthPoints)
        assertEquals(10, symphonist1.shieldPoints)
        assertFalse(symphonist1.isCrippled)
        assertFalse(symphonist1.isRaged)
        assertEquals(15, symphonist2.currentHealthPoints)
        assertEquals(10, symphonist2.shieldPoints)
        assertFalse(symphonist2.isCrippled)
        assertFalse(symphonist2.isRaged)
    }

    @Test
    fun initiateActiveAbility_givenLifestealAbilityAsSymphonist_healsSymphonist() {
        val testEnemy1 = object: Enemy(1, 1, 1, 1, 1, 10, 10) {}
        val symphonist = Symphonist(1, 1, 1, 1, 1, 10, 10)
        val testBundle = AbilityEffectBundle( lifestealDone = 10)
        subject.instanceEnemies.add(testEnemy1)
        subject.instanceParty.orchestra.add(symphonist)
        every { activeAbilityManger.doActiveAbility(any(),any(), any()) } returns testBundle

        subject.initiateActiveAbility(symphonist,  BASIC_PHYSICAL_ATTACK, testEnemy1)

        assertEquals(20, symphonist.currentHealthPoints)
    }

    @Test
    fun initiateActiveAbility_givenLifestealAbilityAsEnemy_healsEnemy() {
        val testEnemy1 = object: Enemy(1, 1, 1, 1, 1, 10, 10) {}
        val symphonist1 = Symphonist(1, 1, 1, 1, 1, 10, 10)
        val testBundle = AbilityEffectBundle( lifestealDone = 10)
        subject.instanceEnemies.add(testEnemy1)
        subject.instanceParty.orchestra.add(symphonist1)
        every { activeAbilityManger.doActiveAbility(any(),any(), any()) } returns testBundle

        subject.initiateActiveAbility(testEnemy1,  BASIC_PHYSICAL_ATTACK, symphonist1)

        assertEquals(20, testEnemy1.currentHealthPoints)
    }

    @Test
    fun initiateActiveAbility_givenNonCripplingOrRagingAbilityAsSymphonist_doesNotChangeRagedOrCrippled() {
        val testEnemy1 = object: Enemy(1, 1, 1, 1, 1, 10, 10, isCrippled = true, isRaged = false) {}
        val symphonist = Symphonist(1, 1, 1, 1, 1, 10, 10)
        val testBundle = AbilityEffectBundle()
        subject.instanceEnemies.add(testEnemy1)
        subject.instanceParty.orchestra.add(symphonist)
        every { activeAbilityManger.doActiveAbility(any(),any(), any()) } returns testBundle

        subject.initiateActiveAbility(symphonist,  BASIC_PHYSICAL_ATTACK, testEnemy1)

        assertTrue(testEnemy1.isCrippled)
        assertFalse(testEnemy1.isRaged)
    }
    //endregion initiateAbility




}