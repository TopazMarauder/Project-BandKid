package com.bandkid.game

import com.bandkid.game.attacks.AttackManager
import com.bandkid.game.attacks.AttackName.BASIC_ATTACK
import com.bandkid.game.creatures.models.Creature
import com.bandkid.game.creatures.models.Enemy
import com.bandkid.game.models.Party
import com.bandkid.game.models.Symphonist
import javax.inject.Inject

class BattleInstance @Inject constructor(val attackManager: AttackManager){


    private val exampleSymphonist  = Symphonist(1,1,1,1,1,1,1)
    val exampleParty = Party(listOf(exampleSymphonist), 1, 1)

    private val exampleEnemy = Enemy(100, 100)
    private val enemies: MutableList<Creature> = mutableListOf(exampleEnemy)


    init {
        val targetedEnemies = listOf<Int>(1,3)
        attackManager.doAttack(exampleParty.orchestra[0], targetedEnemies.map { enemies[it] }, BASIC_ATTACK).let { returnedCreatures ->
            targetedEnemies.map { enemies[it] = returnedCreatures.first() }
        }
    }

}