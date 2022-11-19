package com.bandkid.game.creatures.models

import com.bandkid.game.battle.activeabilities.AbilityName

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
    var moveInQueue: Pair<Creature?, AbilityName>?
}