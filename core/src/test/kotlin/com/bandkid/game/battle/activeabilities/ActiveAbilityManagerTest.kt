package com.bandkid.game.battle.activeabilities

import com.bandkid.game.battle.activeabilities.AbilityName.*
import com.bandkid.game.creatures.models.symphonists.Symphonist
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class ActiveAbilityManagerTest {
    private val activeAbilityEffects = mockk<ActiveAbilityEffects>(relaxed = true)
    private val subject = ActiveAbilityManager(activeAbilityEffects)

    @Test
    fun playCard_givenExampleAttack_callsApplyExampleAttack() {
        val symphonist = mockk<Symphonist>()
        val target = mockk<com.bandkid.game.creatures.models.Creature>()

        subject.doActiveAbility(symphonist, target, BASIC_PHYSICAL_ATTACK)

        verify { activeAbilityEffects.applyBasicPhysicalAttack(symphonist, target) }
    }

    @Test
    fun playCard_givenExampleAttack2_callsApplyExampleAttack2() {
        val symphonist = mockk<Symphonist>()
        val target = mockk<com.bandkid.game.creatures.models.Creature>()

        subject.doActiveAbility(symphonist, target, BASIC_MAGICAL_ATTACK)

        verify { activeAbilityEffects.applyBasicMagicalAttack(symphonist, target) }
    }
}