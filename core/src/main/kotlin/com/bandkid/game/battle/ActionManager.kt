package com.bandkid.game.battle

import com.bandkid.game.battle.activeabilities.AbilityEffectBundle
import com.bandkid.game.battle.activeabilities.ActiveAbilityManager
import com.bandkid.game.battle.activeabilities.AbilityName
import com.bandkid.game.creatures.models.Creature
import com.bandkid.game.creatures.models.enemies.Enemy
import com.bandkid.game.models.Party
import com.bandkid.game.creatures.models.symphonists.Symphonist
import javax.inject.Inject

class ActionManager @Inject constructor(private val activeAbilityManager: ActiveAbilityManager){

    @Inject
    lateinit var instanceParty: Party

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