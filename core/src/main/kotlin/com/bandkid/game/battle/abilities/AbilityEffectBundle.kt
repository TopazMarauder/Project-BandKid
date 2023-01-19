package com.bandkid.game.battle.abilities

data class AbilityEffectBundle(
    val damageDone: Int = 0,
    val healingDone: Int = 0,
    val lifestealDone: Int = 0,
    val shieldingDone: Int = 0,
    val crippleApplied: Boolean? = null,
    val rageApplied: Boolean? = null,
    val resurrectTrigger: Boolean = false
)

