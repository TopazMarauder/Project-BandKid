package com.bandkid.game.creatures.models

import com.bandkid.game.battle.activeabilities.AbilityName
import com.bandkid.game.creatures.models.enemies.Enemy
import com.bandkid.game.creatures.models.symphonists.Symphonist

interface Creature {
    val strength: Int
    val durability: Int
    val intellect: Int
    val constitution: Int
    val agility: Int
    var maxHealthPoints: Int
    var currentHealthPoints: Int
    var shieldPoints: Int
    var isRaged: Boolean
    var isCrippled: Boolean
    var moveSet: MutableList<AbilityName>
    var moveInQueue: Pair<Array<Creature>?, AbilityName>?

    fun queueMove(symphonists: MutableList<Symphonist>, enemies: MutableList<Enemy>)
    fun getQueuedMove(): AbilityName
    fun getQueuedTargets(): Array<Creature>
}