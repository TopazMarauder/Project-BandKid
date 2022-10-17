package com.bandkid.game.creatures.models

interface Creature{
    var maxHealthPoints: Int
    var currentHealthPoints: Int
    fun xz() = 2
}