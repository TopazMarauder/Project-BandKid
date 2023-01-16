package com.bandkid.game.battle

import com.bandkid.game.battle.abilities.AbilityEffectBundle
import com.bandkid.game.battle.abilities.AbilityManager
import com.bandkid.game.battle.abilities.AbilityName
import com.bandkid.game.creatures.models.Creature
import javax.inject.Inject

class ActionManager {

    @Inject
    lateinit var abilityManager: AbilityManager

    /**
     * returns true if lethal damage is dealt during the course of the ability
     **/
    fun initiateActiveAbility(caster: Creature) = initiateAbility(caster, caster.getQueuedMove(), *caster.getQueuedTargets())

    /**
     * returns true if lethal damage is dealt during the course of the ability
     **/
    fun initiatePassiveAbility(caster: Creature) = initiateAbility(caster, caster.getPassiveMove(), *caster.getPassiveTargets())

    /**
     * returns true if lethal damage is dealt during the course of the ability
     **/
    fun initiateDeathAbility(caster: Creature) = initiateAbility(caster, caster.getDeathMove(), *caster.getDeathTargets())

    private fun initiateAbility(caster: Creature, abilityName: AbilityName, vararg targets: Creature) : Boolean {
        var isLethal = false
        targets.map { target ->
            target.applyEffectBundle(
                abilityManager.doAbility(caster, target, abilityName),
                caster
            )?.let { isLethal = it }
        }
        return isLethal
    }

    private fun Creature.applyEffectBundle(effectBundle: AbilityEffectBundle, caster: Creature): Boolean? {
        if (effectBundle.resurrectTrigger) isDead = false
        if (isDead) return null
        currentHealthPoints -= effectBundle.damageDone
        currentHealthPoints += effectBundle.healingDone
        caster.currentHealthPoints += effectBundle.lifestealDone
        shieldPoints += effectBundle.shieldingDone
        effectBundle.crippleApplied?.let { isCrippled = it }
        effectBundle.rageApplied?.let { isRaged = it }
        return if (currentHealthPoints == 0) {
            isDead = true
            true }
        else null
    }

}