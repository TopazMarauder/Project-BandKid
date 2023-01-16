package com.bandkid.game.battle.abilities

import com.bandkid.game.battle.abilities.AbilityName.*
import com.bandkid.game.creatures.models.symphonists.Symphonist
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class AbilityManagerTest {
    private val activeAbilityEffects = mockk<ActiveAbilityEffects>()
    private lateinit var subject : AbilityManager

    @Before
    fun setup() {
        subject = AbilityManager(activeAbilityEffects)
    }

    @Test
    fun doAbility_givenAttack_callsApplyAttack() {
        val symphonist = mockk<Symphonist>()
        val target = mockk<com.bandkid.game.creatures.models.Creature>()
        val expected = mockk<AbilityEffectBundle>()
        every { activeAbilityEffects.applyBasicPhysicalAttack(symphonist, target) } returns expected

        val result = subject.doAbility(symphonist, target, BASIC_PHYSICAL_ATTACK)

        verify { activeAbilityEffects.applyBasicPhysicalAttack(symphonist, target) }
        assertEquals(expected, result)
    }

    @Test
    fun doAbility_givenNoAction_returnsEmptyBundle() {
        val symphonist = mockk<Symphonist>()
        val target = mockk<com.bandkid.game.creatures.models.Creature>()
        val expected = AbilityEffectBundle()

        val result = subject.doAbility(symphonist, target, NO_ACTION)

        assertEquals(expected, result)
    }
}