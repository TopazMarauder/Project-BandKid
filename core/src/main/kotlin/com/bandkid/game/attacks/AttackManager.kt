package com.bandkid.game.attacks

import com.bandkid.game.attacks.AttackName.BASIC_ATTACK
import com.bandkid.game.attacks.AttackName.EXAMPLE_ATTACK2
import com.bandkid.game.creatures.models.Creature
import com.bandkid.game.models.Symphonist
import javax.inject.Inject

class AttackManager @Inject constructor(
    val attackEffects: AttackEffects
) {


    fun doAttack(symphonist: Symphonist, targets: List<Creature>, attackName: AttackName): List<Creature> {
        return when (attackName) {
            BASIC_ATTACK -> attackEffects.applyBasicAttack(symphonist, targets.first())
            EXAMPLE_ATTACK2 -> attackEffects.applyExampleAttack2(symphonist, targets)
        }

    }


}