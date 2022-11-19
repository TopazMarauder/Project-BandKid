package com.bandkid.game.battle

import com.bandkid.game.creatures.models.enemies.Enemy
import com.bandkid.game.creatures.models.symphonists.Symphonist
import com.bandkid.game.player.PlayerProvider
import kotlinx.coroutines.*
import ktx.async.KtxAsync
import ktx.async.newAsyncContext
import javax.inject.Inject

class BattleInstance: BattleLifecycle {

    @Inject
    lateinit var playerProvider: PlayerProvider

    @Inject
    lateinit var enemyProvider: EnemyProvider

    private var orchestra: MutableList<Symphonist> = playerProvider.getOrchestra()
    private var enemies: MutableList<Enemy> = enemyProvider.getEnemies()
    val executor = newAsyncContext(threads = 4)

    init {
        onCreate()
    }

    override fun onCreate() {

        super.onCreate()
    }

    override fun onPassivePhase() {
        super.onPassivePhase()
    }

    override fun onChoicePhase() {
        KtxAsync.launch {
            withContext(Dispatchers.IO) {
                async {}
            }
        }
        super.onChoicePhase()
    }

    override fun onActionPhase() {
        super.onActionPhase()
    }

    override fun onEndPhase() {
        super.onEndPhase()
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }
}