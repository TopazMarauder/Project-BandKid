package com.bandkid.game.creatures.models.enemies

import com.bandkid.game.battle.abilities.AbilityName

class BrassLegionnaire : Enemy(
    9,
    1,
    1,
    1,
    1,
    45,
    45,
    moveSet = mutableListOf(AbilityName.BASIC_PHYSICAL_ATTACK)
) {


}