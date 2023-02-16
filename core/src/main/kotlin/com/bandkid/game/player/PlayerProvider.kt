package com.bandkid.game.player

import com.bandkid.game.creatures.models.symphonists.Symphonist
import com.bandkid.game.models.Coordinates
import com.bandkid.game.models.Party
import javax.inject.Inject
import javax.inject.Singleton

object PlayerProvider {

    private var playerData: PlayerData = PlayerData.emptyData()

    fun getPartySize() = playerData.party.let { Pair(it.currentSize, it.maxSize) }

    fun getOrchestra() = playerData.party.orchestra

    fun setOrchestra(orchestra: MutableList<Symphonist>) { playerData.party.orchestra = orchestra}

    fun getFloor() = 1 //TODO add floor to player data, var, setter?
}