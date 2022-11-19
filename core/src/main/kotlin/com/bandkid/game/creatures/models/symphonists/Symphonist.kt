package com.bandkid.game.creatures.models.symphonists

import com.bandkid.game.battle.activeabilities.AbilityName
import com.bandkid.game.creatures.models.Creature
import com.bandkid.game.models.Item

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
    override var moveInQueue: Pair<Creature?, AbilityName>? = null,
    var equippedItem: Item? = null
) : Creature {
    fun applyItem(){

    }
}