package com.bandkid.game.battle.activeabilities

import com.bandkid.game.battle.activeabilities.AbilityName.AbilityTarget.*
import com.bandkid.game.battle.activeabilities.AbilityName.AbilityType.*
import com.bandkid.game.battle.activeabilities.AbilityName.AbilityType.DARK

enum class AbilityName(val abilityType: AbilityType, val abilityTarget: AbilityTarget)  {
    NO_ACTION(NONE, SELF),
    BASIC_PHYSICAL_ATTACK(NONE, SINGLE),
    BASIC_MAGICAL_ATTACK(DARK, ALL),
    PHYSICAL_LIFESTEAL_ATTACK(NONE, SINGLE);


    enum class AbilityType {
        NONE,
        DARK,
        LIGHT
    }

    enum class AbilityTarget {
        SINGLE,
        TWO,
        THREE,
        ANY,
        ALL,
        SELF,
        SINGLE_FRIENDLY,
        ALL_FRIENDLY
    }

}