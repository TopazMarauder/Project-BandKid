package com.bandkid.game.models

import com.bandkid.game.creatures.models.Creature

data class Symphonist(
    override val strength: Int,
    override val durability: Int,
    override val intellect: Int,
    override val constitution: Int,
    override val agility: Int,
    override var maxHealthPoints: Int,
    override var currentHealthPoints: Int
) : Creature