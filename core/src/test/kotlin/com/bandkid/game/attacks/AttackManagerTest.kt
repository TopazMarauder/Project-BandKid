package com.bandkid.game.attacks

import com.bandkid.game.attacks.AttackName.*
import com.bandkid.game.creatures.models.Creature
import com.bandkid.game.models.Symphonist
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class AttackManagerTest {
    private val attackEffects = mockk<AttackEffects>(relaxed = true)
    private val subject = AttackManager(attackEffects)

    @Test
    fun playCard_givenExampleAttack_callsApplyExampleAttack() {
        val symphonist = mockk<Symphonist>()
        val target = mockk<Creature>()
        val targets = listOf(target)

        subject.doAttack(symphonist, targets, BASIC_ATTACK)

        verify { attackEffects.applyBasicAttack(symphonist, target) }
    }

    @Test
    fun playCard_givenExampleAttack2_callsApplyExampleAttack2() {
        val symphonist = mockk<Symphonist>()
        val target = mockk<Creature>()
        val targets = listOf(target)

        subject.doAttack(symphonist, targets, EXAMPLE_ATTACK2)

        verify { attackEffects.applyExampleAttack2(symphonist, targets) }
    }
}