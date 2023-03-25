package com.bandkid.game.battle.abilities

import com.bandkid.game.creatures.models.Creature
import javax.inject.Inject

class ActiveAbilityEffects {

    fun applyBasicPhysicalAttack(caster: Creature, target: Creature) =
        AbilityEffectBundle(damageDone = calculateDamage(BD_BASIC_PHYSICAL, caster.strength, target.durability))

    fun applyBasicMagicalAttack(caster: Creature, target: Creature) =
        AbilityEffectBundle(damageDone = calculateDamage(BD_BASIC_MAGICAL, caster.intellect, target.constitution))

    fun applyPhysicalLifestealAttack(caster: Creature, target: Creature) =
        AbilityEffectBundle(damageDone = calculateDamage(BD_BASIC_MAGICAL, caster.intellect, target.constitution))

    private fun calculateDamage(baseDamage: Int, offensiveStat: Int, defensiveStat: Int): Int =
        (baseDamage.toDouble() * (offensiveStat.toDouble() / defensiveStat.toDouble())).toInt()

    companion object {

        private const val BD_BASIC_PHYSICAL: Int = 0


        private const val BD_BASIC_MAGICAL: Int = 10

    }
}