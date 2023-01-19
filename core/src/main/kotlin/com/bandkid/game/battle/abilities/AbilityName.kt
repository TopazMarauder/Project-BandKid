package com.bandkid.game.battle.abilities

import com.bandkid.game.battle.abilities.AbilityName.AbilityIntent.ATTACK
import com.bandkid.game.battle.abilities.AbilityName.AbilityIntent.SKIP
import com.bandkid.game.battle.abilities.AbilityName.AbilityTarget.ALL
import com.bandkid.game.battle.abilities.AbilityName.AbilityTarget.SINGLE
import com.bandkid.game.battle.abilities.AbilityName.AbilityTarget.SELF
import com.bandkid.game.battle.abilities.AbilityName.AbilityType.DARK
import com.bandkid.game.battle.abilities.AbilityName.AbilityType.NONE

enum class AbilityName(val abilityType: AbilityType, val abilityTarget: AbilityTarget, val abilityIntent: AbilityIntent)  {
    //Default
    NO_ACTION(NONE, SELF, SKIP),

    //Active
    BASIC_PHYSICAL_ATTACK(NONE, SINGLE, ATTACK),
    BASIC_MAGICAL_ATTACK(DARK, ALL, ATTACK),
    PHYSICAL_LIFESTEAL_ATTACK(NONE, SINGLE, ATTACK),

    //Death
    NO_ACTION_DEATH(NONE, SELF, SKIP),

    //Passive
    NO_ACTION_PASSIVE(NONE, SELF, SKIP)

    ;


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