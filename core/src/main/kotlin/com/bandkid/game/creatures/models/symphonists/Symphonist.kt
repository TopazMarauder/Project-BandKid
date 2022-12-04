package com.bandkid.game.creatures.models.symphonists

import com.bandkid.game.battle.activeabilities.AbilityName
import com.bandkid.game.creatures.models.Creature
import com.bandkid.game.creatures.models.enemies.Enemy
import com.bandkid.game.models.Item
import com.bandkid.game.utils.SeedManager
import kotlin.random.Random

class Symphonist(
    override val strength: Int,
    override val durability: Int,
    override val intellect: Int,
    override val constitution: Int,
    override val agility: Int,
    override var maxHealthPoints: Int,
    override var currentHealthPoints: Int,
    override var shieldPoints: Int = 0,
    override var isRaged: Boolean = false,
    override var isCrippled: Boolean = false,
    override var moveSet: MutableList<AbilityName> = mutableListOf(),
    override var moveInQueue: Pair<List<Creature>?, AbilityName>? = null,
    var equippedItem: Item? = null
) : Creature {
    fun applyItem(){

    }

    fun queueMove(symphonists: MutableList<Symphonist>, enemies: MutableList<Enemy>) {
        moveInQueue = Pair(selectTarget(enemies), selectAbility())
    }

    private fun selectAbility() = Random(SeedManager.getSeed()).nextInt(0, moveSet.size).let { moveSet[it] }

    //Assumes most enemies will usually target symphonists, randomly
    private fun selectTarget(symphonists: MutableList<Enemy>) = listOf(Random(SeedManager.getSeed()).nextInt(0, symphonists.size).let { symphonists[it] })


}