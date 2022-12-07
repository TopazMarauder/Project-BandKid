package com.bandkid.game.battle

import com.bandkid.game.battle.activeabilities.AbilityEffectBundle
import com.bandkid.game.battle.activeabilities.ActiveAbilityManager
import com.bandkid.game.battle.activeabilities.AbilityName
import com.bandkid.game.creatures.models.Creature
import com.bandkid.game.creatures.models.enemies.Enemy
import com.bandkid.game.models.Party
import javax.inject.Inject

class ActionManager @Inject constructor(private val activeAbilityManager: ActiveAbilityManager){

    @Inject
    lateinit var instanceParty: Party

    val instanceEnemies: MutableList<Enemy> = mutableListOf()

    fun initiateActiveAbility(caster: Creature, abilityName: AbilityName, vararg targets: Creature) {
        targets.map { target ->
            target.applyEffectBundle(
                activeAbilityManager.doActiveAbility(caster, target, abilityName),
                caster
            )
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