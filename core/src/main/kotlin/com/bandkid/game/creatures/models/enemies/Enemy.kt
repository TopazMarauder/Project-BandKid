package com.bandkid.game.creatures.models.enemies

import com.bandkid.game.battle.activeabilities.AbilityName
import com.bandkid.game.creatures.models.Creature
import com.bandkid.game.creatures.models.symphonists.Symphonist
import com.bandkid.game.utils.SeedManager
import kotlin.random.Random

abstract class Enemy(
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
    override var isDead: Boolean = false,
    override var moveSet: MutableList<AbilityName> = mutableListOf(),
    override var moveInQueue: Pair<Array<Creature>?, AbilityName>? = null
) : Creature {

    override fun queueMove(symphonists: MutableList<Symphonist>, enemies: MutableList<Enemy>) {
        moveInQueue = Pair(selectTarget(symphonists), selectAbility())
    }

    override fun getQueuedMove(): AbilityName = moveInQueue?.second ?: AbilityName.NO_ACTION

    override fun getQueuedTargets(): Array<Creature> = moveInQueue?.first ?: arrayOf()

    private fun selectAbility() = Random(SeedManager.getSeed()).nextInt(0, moveSet.size).let { moveSet[it] }

    //Assumes most enemies will usually target symphonists, randomly
    private fun selectTarget(symphonists: MutableList<Symphonist>): Array<Creature> = arrayOf(Random(SeedManager.getSeed()).nextInt(0, symphonists.size).let { symphonists[it] })


}
