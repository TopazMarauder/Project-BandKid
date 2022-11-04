package com.bandkid.game

import com.bandkid.game.activeabilities.AbilityEffectBundle
import com.bandkid.game.activeabilities.ActiveAbilityManager
import com.bandkid.game.activeabilities.AbilityName
import com.bandkid.game.activeabilities.AbilityName.BASIC_PHYSICAL_ATTACK
import com.bandkid.game.creatures.models.Creature
import com.bandkid.game.creatures.models.Enemy
import com.bandkid.game.models.Party
import com.bandkid.game.models.Symphonist
import javax.inject.Inject

class BattleInstance @Inject constructor(private val activeAbilityManager: ActiveAbilityManager){

    val instanceParty = Party(mutableListOf(), 1, 1)
    val instanceEnemies: MutableList<Enemy> = mutableListOf()



    fun initiateDefensiveActiveAbility(caster: Creature, targets: List<Int>){

    }

    fun initiateOffensiveActiveAbility(caster: Creature, targets: List<Int>, abilityName: AbilityName) {
        targets.map { target ->
            if (caster is Symphonist)
                instanceEnemies[target].applyEffectBundle(
                    activeAbilityManager.doActiveAbility(caster, instanceEnemies[target], abilityName),
                    caster
                )
            else {
                instanceParty.orchestra[target].applyEffectBundle(
                    activeAbilityManager.doActiveAbility(caster, instanceParty.orchestra[target], abilityName),
                    caster
                )
            }
        }
    }

    private fun Creature.applyEffectBundle(effectBundle: AbilityEffectBundle, caster: Creature){
        currentHealthPoints -= effectBundle.damageDone
        currentHealthPoints += effectBundle.healingDone
        caster.currentHealthPoints += effectBundle.lifestealDone
        shieldPoints += effectBundle.shieldingDone
        effectBundle.crippleApplied?.let { isCrippled = it }
        effectBundle.rageApplied?.let { isRaged = it }
    }

}