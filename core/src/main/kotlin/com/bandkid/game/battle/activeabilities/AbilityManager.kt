package com.bandkid.game.battle.activeabilities

import com.bandkid.game.battle.activeabilities.AbilityName.*
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
            BASIC_DEATH_ABILITY -> EMPTY_BUNDLE //TODO FILL
    }

    companion object{
        val EMPTY_BUNDLE = AbilityEffectBundle()
    }
}