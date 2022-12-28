package com.bandkid.game.battle

import com.bandkid.game.battle.activeabilities.AbilityName
import com.bandkid.game.creatures.models.Creature
import com.bandkid.game.creatures.models.enemies.Enemy
import com.bandkid.game.creatures.models.symphonists.Symphonist
import com.bandkid.game.player.PlayerProvider
import com.bandkid.game.utils.SeedManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ktx.async.KTX
import ktx.async.KtxAsync
import ktx.async.newSingleThreadAsyncContext
import javax.inject.Inject

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
    private val playerExecutor : CoroutineDispatcher = newSingleThreadAsyncContext()
    private val defaultExecutor : CoroutineDispatcher = Dispatchers.KTX

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
        scope.launch {
            performActions()
        }
        super.onActionPhase()
    }

    override fun onEndPhase() {

    }

    override fun onDestroy() {
    }

    private suspend fun getEnemyActions() {
        withContext(defaultExecutor) {
            for (enemy in enemies) {
                if (!enemy.isDead) enemy.queueMove(orchestra, enemies)
            }
        }
    }

    private suspend fun performActions() {
        withContext(defaultExecutor) {
            (orchestra + enemies)
                .sortedBy { agilitySort(it.agility) }
                .forEach { caster ->
                    validateAction(caster)
                }
        }
    }

    private fun validateAction(caster: Creature) {
        if (!caster.isDead) actionManager.initiateAbility(caster, caster.getQueuedMove(), *caster.getQueuedTargets())
    }

    private fun checkDeaths(){
        (orchestra+enemies)
            .filter { it.shouldActivateDeathAbility ?: false }
            .sortedBy { agilitySort(it.agility) }
            .forEach{ caster ->
                actionManager.initiateAbility(caster, caster.getDeathMove(), *caster.getDeathTargets())
            }

    }

    private fun agilitySort(agility: Int) = -1 * (agility.toDouble() + SeedManager.getDouble(0.0, 0.9))

}