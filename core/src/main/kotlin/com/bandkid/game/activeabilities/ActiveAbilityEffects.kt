package com.bandkid.game.activeabilities

import com.bandkid.game.creatures.models.Creature
import com.bandkid.game.models.Symphonist
import javax.inject.Inject

class ActiveAbilityEffects @Inject constructor() {

    fun applyBasicPhysicalAttack(symphonist: Symphonist, target: Creature) =
        AbilityEffectBundle(damageDone = calculateDamage(BD_BASIC_PHYSICAL, symphonist.strength, target.durability))

    fun applyBasicMagicalAttack(symphonist: Symphonist, target: Creature) =
        AbilityEffectBundle(damageDone = calculateDamage(BD_BASIC_MAGICAL, symphonist.intellect, target.constitution))

    private fun calculateDamage(baseDamage: Int, offensiveStat: Int, defensiveStat: Int): Int =
        (baseDamage.toDouble() * (offensiveStat.toDouble() / defensiveStat.toDouble())).toInt()

    companion object {

        private const val BD_BASIC_PHYSICAL: Int = 0


        private const val BD_BASIC_MAGICAL: Int = 10

    }
}