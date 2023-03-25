package com.bandkid.game.creatures.models.enemies

import com.bandkid.game.battle.abilities.AbilityEffectBundle
import com.bandkid.game.battle.abilities.AbilityName
import com.bandkid.game.creatures.models.Creature


//BeastMaster Boss if hp drops to below 50%, do not deal damage, instead initate ability which deals damage to summoned brasset hound if able.
class BeastMasterBoss : Enemy(9,
1,
1,
1,
1,
45,
45,
moveSet = mutableListOf(AbilityName.BASIC_PHYSICAL_ATTACK)) {

    override fun applyEffectBundle(effectBundle: AbilityEffectBundle, caster: Creature): Boolean? {
        TODO()
    }

} 