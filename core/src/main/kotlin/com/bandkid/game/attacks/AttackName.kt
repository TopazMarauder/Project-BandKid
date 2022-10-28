package com.bandkid.game.attacks

import com.bandkid.game.attacks.AttackName.AttackTarget.*
import com.bandkid.game.attacks.AttackName.AttackType.*
import com.bandkid.game.attacks.AttackName.AttackType.DARK

enum class AttackName(val attackType: AttackType, val attackTarget: AttackTarget)  {
    BASIC_ATTACK(NONE, SINGLE),
    EXAMPLE_ATTACK2(DARK, ALL);

    enum class AttackType {
        NONE,
        DARK,
        LIGHT
    }

    enum class AttackTarget {
        SINGLE,
        TWO,
        THREE,
        ANY,
        ALL,
        SINGLEFRIENDLY,
        ALLFRIENDLY
    }

}