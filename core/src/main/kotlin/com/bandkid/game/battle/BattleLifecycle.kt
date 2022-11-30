package com.bandkid.game.battle

import com.badlogic.gdx.LifecycleListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

interface BattleLifecycle {

     fun onCreate() { onPassivePhase() }
     fun onPassivePhase() { onChoicePhase() }
     fun onChoicePhase() { onActionPhase() }
     fun onActionPhase() { onEndPhase() }
     fun onEndPhase() { onPassivePhase() }
     fun onDestroy() { }

}
