package com.bandkid.game.creatures.models.enemies

import com.bandkid.game.battle.abilities.AbilityName
import com.bandkid.game.creatures.models.Creature
import com.bandkid.game.creatures.models.symphonists.Symphonist
import com.bandkid.game.utils.SeedManager

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
    override var shouldActivateDeathAbility: Boolean? = null,
    override val deathAbility: AbilityName? = null,
    override val passiveAbility: AbilityName? = null,
    override var moveSet: MutableList<AbilityName> = mutableListOf(),
    override var moveInQueue: Pair<Array<Creature>?, AbilityName>? = null
) : Creature {

    override fun queueMove(symphonists: MutableList<Symphonist>, enemies: MutableList<Enemy>) {
        moveInQueue = Pair(selectTarget(symphonists), selectAbility())
    }

    override fun getQueuedMove(): AbilityName = moveInQueue?.second ?: AbilityName.NO_ACTION

    override fun getQueuedTargets(): Array<Creature> = moveInQueue?.first ?: arrayOf()

    override fun getPassiveMove(): AbilityName = passiveAbility ?: AbilityName.NO_ACTION

    override fun getPassiveTargets(): Array<Creature> = arrayOf()

    override fun getDeathMove(): AbilityName = deathAbility ?: AbilityName.NO_ACTION

    override fun getDeathTargets(): Array<Creature> = arrayOf()

    private fun selectAbility() = SeedManager.getInt(0, moveSet.size).let { moveSet[it] }

    //Assumes most enemies will usually target symphonists, randomly
    private fun selectTarget(symphonists: MutableList<Symphonist>): Array<Creature> = arrayOf(SeedManager.getInt(0, symphonists.size).let { symphonists[it] })


}
