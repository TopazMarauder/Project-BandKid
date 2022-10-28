package com.bandkid.game.creatures.models

data class Enemy( override var maxHealthPoints: Int,
                  override var currentHealthPoints: Int) : Creature
