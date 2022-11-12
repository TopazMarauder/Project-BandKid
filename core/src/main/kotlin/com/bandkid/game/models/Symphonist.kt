package com.bandkid.game.models

import com.bandkid.game.battle.activeabilities.AbilityName
import com.bandkid.game.creatures.models.Creature

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
    var moveSet: MutableList<AbilityName> = mutableListOf(),
    var equippedItem: Item? = null
) : Creature {
    fun applyItem(){

    }
}