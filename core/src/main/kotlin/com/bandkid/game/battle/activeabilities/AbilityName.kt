package com.bandkid.game.battle.activeabilities

import com.bandkid.game.battle.activeabilities.AbilityName.AbilityIntent.ATTACK
import com.bandkid.game.battle.activeabilities.AbilityName.AbilityIntent.SKIP
import com.bandkid.game.battle.activeabilities.AbilityName.AbilityTarget.ALL
import com.bandkid.game.battle.activeabilities.AbilityName.AbilityTarget.SINGLE
import com.bandkid.game.battle.activeabilities.AbilityName.AbilityTarget.SELF
import com.bandkid.game.battle.activeabilities.AbilityName.AbilityType.DARK
import com.bandkid.game.battle.activeabilities.AbilityName.AbilityType.NONE

enum class AbilityName(val abilityType: AbilityType, val abilityTarget: AbilityTarget, val abilityIntent: AbilityIntent)  {
    NO_ACTION(NONE, SELF, SKIP),
    BASIC_PHYSICAL_ATTACK(NONE, SINGLE, ATTACK),
    BASIC_MAGICAL_ATTACK(DARK, ALL, ATTACK),
    PHYSICAL_LIFESTEAL_ATTACK(NONE, SINGLE, ATTACK),
    BASIC_DEATH_ABILITY(NONE, ALL, ATTACK);


    enum class AbilityIntent {
        ATTACK,
        DEFEND,
        BUFF,
        DEBUFF,
        SPECIAL,
        HIDDEN,
        SKIP;

    }

    enum class AbilityTarget {
        SINGLE,
        TWO,
        THREE,
        ANY,
        ALL,
        SELF,
        SINGLE_FRIENDLY,
        ALL_FRIENDLY;
    }

    enum class AbilityType {
        NONE,
        DARK,
        LIGHT;
    }
}