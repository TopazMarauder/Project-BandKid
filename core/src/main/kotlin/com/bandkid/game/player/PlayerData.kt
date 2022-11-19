package com.bandkid.game.player

import com.bandkid.game.models.Coordinates
import com.bandkid.game.models.Party

data class PlayerData(
    val party: Party,
    val location: Coordinates,
)
