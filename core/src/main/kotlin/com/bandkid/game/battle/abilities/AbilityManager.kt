package com.bandkid.game.battle.abilities

import com.bandkid.game.battle.abilities.AbilityName.*
import com.bandkid.game.creatures.models.Creature
import javax.inject.Inject

class AbilityManager @Inject constructor(
    private val activeAbilityEffects: ActiveAbilityEffects
) {

    fun doAbility(caster: Creature, target: Creature, abilityName: AbilityName): AbilityEffectBundle =
        when (abilityName) {
            NO_ACTION -> EMPTY_BUNDLE
            BASIC_PHYSICAL_ATTACK -> activeAbilityEffects.applyBasicPhysicalAttack(caster, target)
            BASIC_MAGICAL_ATTACK -> activeAbilityEffects.applyBasicMagicalAttack(caster, target)
            PHYSICAL_LIFESTEAL_ATTACK -> activeAbilityEffects.applyPhysicalLifestealAttack(caster, target)
            NO_ACTION_DEATH -> EMPTY_BUNDLE
            NO_ACTION_PASSIVE -> EMPTY_BUNDLE
    }

    companion object{
        val EMPTY_BUNDLE = AbilityEffectBundle()
    }
}