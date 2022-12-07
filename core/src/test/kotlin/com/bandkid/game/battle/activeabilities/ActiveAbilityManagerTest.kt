package com.bandkid.game.battle.activeabilities

import com.bandkid.game.battle.activeabilities.AbilityName.*
import com.bandkid.game.creatures.models.symphonists.Symphonist
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import kotlin.math.exp
import kotlin.test.assertEquals

class ActiveAbilityManagerTest {
    private val activeAbilityEffects = mockk<ActiveAbilityEffects>()
    private lateinit var subject : ActiveAbilityManager


    @Before
    fun setup() {
        subject = ActiveAbilityManager(activeAbilityEffects)
    }

    @Test
    fun doActiveAbility_givenAttack_callsApplyAttack() {
        val symphonist = mockk<Symphonist>()
        val target = mockk<com.bandkid.game.creatures.models.Creature>()
        val expected = mockk<AbilityEffectBundle>()
        every { activeAbilityEffects.applyBasicPhysicalAttack(symphonist, target) } returns expected

        val result = subject.doActiveAbility(symphonist, target, BASIC_PHYSICAL_ATTACK)

        verify { activeAbilityEffects.applyBasicPhysicalAttack(symphonist, target) }
        assertEquals(expected, result)
    }

    @Test
    fun doActiveAbility_givenNoAction_returnsEmptyBundle() {
        val symphonist = mockk<Symphonist>()
        val target = mockk<com.bandkid.game.creatures.models.Creature>()
        val expected = AbilityEffectBundle()

        val result = subject.doActiveAbility(symphonist, target, NO_ACTION)

        assertEquals(expected, result)
    }
}