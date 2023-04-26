package com.bandkid.game.player

import com.bandkid.game.player.models.Coordinates
import com.bandkid.game.player.models.Party

data class PlayerData(
    val party: Party,
    val location: Coordinates,
){
    companion object{
        fun emptyData() = PlayerData(Party(mutableListOf(), 0, 4), Coordinates())
    }
}
