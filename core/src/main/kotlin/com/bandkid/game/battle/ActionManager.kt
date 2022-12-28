package com.bandkid.game.battle

import com.bandkid.game.battle.activeabilities.AbilityEffectBundle
import com.bandkid.game.battle.activeabilities.AbilityManager
import com.bandkid.game.battle.activeabilities.AbilityName
import com.bandkid.game.creatures.models.Creature
import com.bandkid.game.creatures.models.enemies.Enemy
import javax.inject.Inject

class ActionManager {

    @Inject
    lateinit var abilityManager: AbilityManager

    fun initiateAbility(caster: Creature, abilityName: AbilityName, vararg targets: Creature) {
        targets.map { target ->
            target.applyEffectBundle(
                abilityManager.doAbility(caster, target, abilityName),
                caster
            )
        }
    }

    private fun Creature.applyEffectBundle(effectBundle: AbilityEffectBundle, caster: Creature){
        if (effectBundle.resurrectTrigger) isDead = false
        currentHealthPoints -= effectBundle.damageDone
        currentHealthPoints += effectBundle.healingDone
        caster.currentHealthPoints += effectBundle.lifestealDone
        shieldPoints += effectBundle.shieldingDone
        effectBundle.crippleApplied?.let { isCrippled = it }
        effectBundle.rageApplied?.let { isRaged = it }
        if (currentHealthPoints == 0) isDead = true
        if (isDead) currentHealthPoints = 0
    }

}