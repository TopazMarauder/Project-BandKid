package com.bandkid.game.battle

import com.bandkid.game.creatures.models.enemies.Enemy
import com.bandkid.game.creatures.models.symphonists.Symphonist
import com.bandkid.game.player.PlayerProvider
import com.bandkid.game.utils.SeedManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ktx.async.KTX
import ktx.async.KtxAsync
import ktx.async.newSingleThreadAsyncContext
import javax.inject.Inject
import javax.swing.Action

class BattleInstance: BattleLifecycle {

    @Inject
    lateinit var playerProvider: PlayerProvider

    @Inject
    lateinit var enemyProvider: EnemyProvider

    @Inject
    lateinit var actionManager: ActionManager

    private val orchestra: MutableList<Symphonist> by lazy { playerProvider.getOrchestra()}
    private val enemies: MutableList<Enemy> by lazy {enemyProvider.getEnemies() }
    private val scope = KtxAsync
    private val playerExecutor = newSingleThreadAsyncContext()

    override fun onCreate() {
        super.onCreate()
    }

    override fun onPassivePhase() {
        super.onPassivePhase()
    }

    override fun onChoicePhase()  {
        scope.launch {
                getEnemyActions()
            withContext(playerExecutor) {

            }
        }
        super.onChoicePhase()
    }

    override fun onActionPhase() {
        super.onActionPhase()
        (orchestra + enemies).sortedBy { SeedManager.getDouble() }.forEach{

        }
    }

    override fun onEndPhase() {

    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }

    private suspend fun getEnemyActions() {
        withContext(Dispatchers.KTX) {
            for (enemy in enemies) {
                enemy.queueMove(orchestra, enemies)
            }
        }
    }

    private suspend fun performActions() {

    }




}