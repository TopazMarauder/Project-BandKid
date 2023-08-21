package com.bandkid.game.battle

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

class BattleInstance: BattleLifecycle {



    lateinit var actionManager: ActionManager

    private val orchestra: MutableList<Symphonist> by lazy { PlayerProvider.getOrchestra()}
    private val enemies: MutableList<Enemy> by lazy {EnemyProvider.getCacophony() }
    private val scope = KtxAsync
    private val playerExecutor : CoroutineDispatcher = newSingleThreadAsyncContext()
    private val defaultExecutor : CoroutineDispatcher = Dispatchers.KTX

    override fun onCreate() {
        super.onCreate()
    }

    override fun onPassivePhase() {
        scope.launch {
            performPassives()
        }
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
        if (enemiesWiped() || orchestraWiped()){
            onDestroy()
        } else {
            onPassivePhase()
        }
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

    private suspend fun performPassives() {
        withContext(defaultExecutor) {
            getSortedCharacters()
                .filter { validatePassive(it) }
                .map { checkDeaths() }
        }
    }

    private suspend fun performActions() {
        withContext(defaultExecutor) {
            getSortedCharacters()
                .filter { validateAction(it) }
                .map { checkDeaths() }
        }
    }

    private fun validatePassive(caster: Creature) : Boolean{
        return if (!caster.isDead) {
            actionManager.initiatePassiveAbility(caster)
        } else false
    }

    private fun validateAction(caster: Creature) : Boolean{
        return if (!caster.isDead) {
            actionManager.initiateActiveAbility(caster)
        } else false
    }

    private fun checkDeaths(){
        (orchestra+enemies)
            .filter { it.shouldActivateDeathAbility ?: false }
            .sortedBy { agilitySort(it.agility) }
            .filter{ caster ->
                actionManager.initiateDeathAbility(caster)
            }
            .map{ checkDeaths()}

    }

    private fun enemiesWiped() = enemies.all { it.isDead }

    private fun orchestraWiped() = orchestra.all { it.isDead }

    private fun getSortedCharacters() = (orchestra + enemies).sortedBy { agilitySort(it.agility) }

    private fun agilitySort(agility: Int) = -1 * (agility.toDouble() + SeedManager.getDouble(0.0, 0.9))

}