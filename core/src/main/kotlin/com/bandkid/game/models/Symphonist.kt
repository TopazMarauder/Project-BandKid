package com.bandkid.game.models

import com.bandkid.game.creatures.models.Creature

data class Symphonist(val strength: Int,
                 val durability: Int,
                 val intellect: Int,
                 val constitution: Int,
                 val agility: Int,
                 override var maxHealthPoints: Int,
                 override var currentHealthPoints: Int
                 ) : Creature