package com.bandkid.game.models

import com.bandkid.game.creatures.models.Creature

class Symphonist(hp: Int) : Creature {
    override var maxHealthPoints: Int = hp
    override var currentHealthPoints = hp
}