package com.bandkid.game.creatures.models

data class Enemy(
    override var maxHealthPoints: Int,
    override var currentHealthPoints: Int,
    override val strength: Int,
    override val durability: Int,
    override val intellect: Int,
    override val constitution: Int,
    override val agility: Int
) : Creature
