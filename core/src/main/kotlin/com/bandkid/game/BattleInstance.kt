package com.bandkid.game

import com.bandkid.game.activeabilities.ActiveAbilityManager
import com.bandkid.game.activeabilities.AbilityName
import com.bandkid.game.activeabilities.AbilityName.BASIC_PHYSICAL_ATTACK
import com.bandkid.game.creatures.models.Creature
import com.bandkid.game.creatures.models.Enemy
import com.bandkid.game.models.Party
import com.bandkid.game.models.Symphonist
import javax.inject.Inject

class BattleInstance @Inject constructor(val activeAbilityManager: ActiveAbilityManager){


    private val exampleSymphonist  = Symphonist(1,1,1,1,1,1,1)
    val exampleParty = Party(listOf(exampleSymphonist), 1, 1)

    private val exampleEnemy1 = Enemy(100, 100)
    private val exampleEnemy2 = Enemy(100, 100)
    private val exampleEnemy3 = Enemy(100, 100)
    private val enemies: MutableList<Creature> = mutableListOf(exampleEnemy1, exampleEnemy2, exampleEnemy3)


    init {
        val targetedEnemies = listOf<Int>(1,3)
        initiateOffensiveActiveAbility(exampleParty.orchestra[0], targetedEnemies, enemies, BASIC_PHYSICAL_ATTACK)

    }

    private fun initiateDefensiveActiveAbility(symphonist: Symphonist, targets: List<Int>, party: Party ){

    }

    private fun initiateOffensiveActiveAbility(symphonist: Symphonist, targets: List<Int>, enemies: List<Creature>, abilityName: AbilityName) {
        targets.map {
            activeAbilityManager.doActiveAbility(symphonist, enemies[it], abilityName)
        }

    }

}