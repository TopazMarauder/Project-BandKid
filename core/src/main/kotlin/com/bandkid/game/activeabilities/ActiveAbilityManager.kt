package com.bandkid.game.activeabilities

import com.bandkid.game.activeabilities.AbilityName.*
import com.bandkid.game.creatures.models.Creature
import com.bandkid.game.models.Symphonist
import javax.inject.Inject

class ActiveAbilityManager @Inject constructor(
    val activeAbilityEffects: ActiveAbilityEffects
) {


    fun doActiveAbility(symphonist: Symphonist, target: Creature, abilityName: AbilityName): AbilityEffectBundle {
        return when (abilityName) {
            BASIC_PHYSICAL_ATTACK -> activeAbilityEffects.applyBasicPhysicalAttack(symphonist, target)
            BASIC_MAGICAL_ATTACK -> activeAbilityEffects.applyBasicMagicalAttack(symphonist, target)
        }

    }


}