package com.bandkid.game.player

import com.bandkid.game.models.Coordinates
import com.bandkid.game.models.Party
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerManager @Inject constructor(private var playerData :PlayerData) {

    fun getPartySize() = playerData.party.let { Pair(it.currentSize, it.maxSize) }

}