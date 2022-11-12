package com.bandkid.game.battle.activeabilities

data class AbilityEffectBundle(
    val damageDone: Int = 0,
    val healingDone: Int = 0,
    val lifestealDone: Int = 0,
    val shieldingDone: Int = 0,
    val crippleApplied: Boolean? = null,
    val rageApplied: Boolean? = null
)

