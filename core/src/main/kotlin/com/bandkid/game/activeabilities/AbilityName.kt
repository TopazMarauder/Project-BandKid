package com.bandkid.game.activeabilities

import com.bandkid.game.activeabilities.AbilityName.AbilityTarget.*
import com.bandkid.game.activeabilities.AbilityName.AbilityType.*
import com.bandkid.game.activeabilities.AbilityName.AbilityType.DARK

enum class AbilityName(val abilityType: AbilityType, val abilityTarget: AbilityTarget)  {
    BASIC_PHYSICAL_ATTACK(NONE, SINGLE),
    BASIC_MAGICAL_ATTACK(DARK, ALL);

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
        SINGLE_FRIENDLY,
        ALL_FRIENDLY
    }

}