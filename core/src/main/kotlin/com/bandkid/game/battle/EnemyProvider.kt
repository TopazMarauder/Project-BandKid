package com.bandkid.game.battle

import com.bandkid.game.creatures.models.enemies.Enemy


object EnemyProvider {

    private var enemies: MutableList<Enemy> = mutableListOf()


    //TODO Logic for determining enemy spawns here
    //Placeholder enemy set

    fun getCacophony() = enemies

    fun setCacophony(cacophony: MutableList<Enemy>) = cacophony.also { enemies = it }


}
