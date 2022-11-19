package com.bandkid.game.battle

import com.badlogic.gdx.LifecycleListener

interface BattleLifecycle {

     fun onCreate() { onPassivePhase() }
     fun onPassivePhase() { onChoicePhase() }
     fun onChoicePhase() { onActionPhase() }
     fun onActionPhase() { onEndPhase() }
     fun onEndPhase() { onPassivePhase() }
     fun onDestroy()

}
