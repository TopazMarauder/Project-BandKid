package com.bandkid.game.attacks

import com.bandkid.game.creatures.models.Creature
import com.bandkid.game.models.Symphonist
import javax.inject.Inject

class AttackEffects @Inject constructor() {

    fun applyBasicAttack(symphonist: Symphonist, target: Creature): List<Creature> {

        symphonist.strength
        return listOf(target)
    }

    fun applyExampleAttack2(symphonist: Symphonist, targets: List<Creature>): List<Creature> {
        return emptyList<Creature>()
    }
}