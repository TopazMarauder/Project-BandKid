package com.bandkid.game.battle

import com.bandkid.game.AsyncTest
import com.bandkid.game.battle.activeabilities.AbilityName.BASIC_PHYSICAL_ATTACK
import com.bandkid.game.creatures.models.enemies.Enemy
import com.bandkid.game.creatures.models.symphonists.Symphonist
import com.bandkid.game.player.PlayerProvider
import com.bandkid.game.utils.SeedManager
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import ktx.async.onRenderingThread
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BattleInstanceTest : AsyncTest() {

    private val playerProvider = mockk<PlayerProvider>()
    private val enemyProvider = mockk<EnemyProvider>()
    private val actionManager = mockk<ActionManager>(relaxed = true)

    private lateinit var subject: BattleInstance


    @Before
    fun setup() {
        SeedManager.getSeed()

        subject = BattleInstance()
        subject.playerProvider = playerProvider
        subject.enemyProvider = enemyProvider
        subject.actionManager = actionManager
    }

    //region onChoicePhase
    @Test
    fun onChoicePhase_getsEnemyMoves() {
        val orchestra = mockk<MutableList<Symphonist>>(relaxed = true)
        val enemy1 = mockk<Enemy>(relaxed = true)
        val enemy2 = mockk<Enemy>(relaxed = true)
        val enemies = mutableListOf<Enemy>(enemy1, enemy2)
        every { playerProvider.getOrchestra() } returns orchestra
        every { enemyProvider.getEnemies() } returns enemies

        runTest { onRenderingThread { subject.onChoicePhase() } }

        verify { enemy1.queueMove(orchestra, enemies) }
        verify { enemy2.queueMove(orchestra, enemies) }
    }
    //endregion onChoicePhase

    //region onActionPhase
    @Test
    fun onActionPhase_givenActions_callsInitiateActiveAbilityOrderedByAgility() {
        val symphonist1 = mockk<Symphonist> { }
        val symphonist2 = mockk<Symphonist> { }
        val enemy1 = mockk<Enemy> { }
        val enemy2 = mockk<Enemy> { }
        val orchestra = mutableListOf(symphonist1, symphonist2)
        val enemies = mutableListOf(enemy1, enemy2)
        every { playerProvider.getOrchestra() } returns orchestra
        every { enemyProvider.getEnemies() } returns enemies
        enemy2.apply {
            every { agility } returns 4
            every { getQueuedMove() } returns BASIC_PHYSICAL_ATTACK
            every { getQueuedTargets() } returns arrayOf(symphonist2)
        }
        symphonist2.apply {
            every { agility } returns 3
            every { getQueuedMove() } returns BASIC_PHYSICAL_ATTACK
            every { getQueuedTargets() } returns arrayOf(enemy2)
        }
        enemy1.apply {
            every { agility } returns 2
            every { getQueuedMove() } returns BASIC_PHYSICAL_ATTACK
            every { getQueuedTargets() } returns arrayOf(symphonist1)
        }
        symphonist1.apply {
            every { agility } returns 1
            every { getQueuedMove() } returns BASIC_PHYSICAL_ATTACK
            every { getQueuedTargets() } returns arrayOf(enemy1, enemy2)
        }
        mockkObject(SeedManager)
        every { SeedManager.getDouble(0.0, 0.9) } returnsMany listOf(.1, .2, .3, .4, .5, .6, .7, .8)

        runTest { onRenderingThread { subject.onActionPhase() } }

        verifyOrder {
            actionManager.initiateActiveAbility(enemy2, BASIC_PHYSICAL_ATTACK, symphonist2)
            actionManager.initiateActiveAbility(symphonist2, BASIC_PHYSICAL_ATTACK, enemy2)
            actionManager.initiateActiveAbility(enemy1, BASIC_PHYSICAL_ATTACK, symphonist1)
            actionManager.initiateActiveAbility(symphonist1, BASIC_PHYSICAL_ATTACK, enemy1, enemy2)
        }
    }

    @Test
    fun onActionPhase_givenActionsWithEqualAgility_callsInitiateActiveAbilityOrderedByAgility() {
        val symphonist1 = mockk<Symphonist> { }
        val symphonist2 = mockk<Symphonist> { }
        val enemy1 = mockk<Enemy> { }
        val enemy2 = mockk<Enemy> { }
        val orchestra = mutableListOf(symphonist1, symphonist2)
        val enemies = mutableListOf(enemy1, enemy2)
        every { playerProvider.getOrchestra() } returns orchestra
        every { enemyProvider.getEnemies() } returns enemies
        enemy2.apply {
            every { agility } returns 3
            every { getQueuedMove() } returns BASIC_PHYSICAL_ATTACK
            every { getQueuedTargets() } returns arrayOf(symphonist2)
        }
        symphonist2.apply {
            every { agility } returns 3
            every { getQueuedMove() } returns BASIC_PHYSICAL_ATTACK
            every { getQueuedTargets() } returns arrayOf(enemy2)
        }
        enemy1.apply {
            every { agility } returns 3
            every { getQueuedMove() } returns BASIC_PHYSICAL_ATTACK
            every { getQueuedTargets() } returns arrayOf(symphonist1)
        }
        symphonist1.apply {
            every { agility } returns 3
            every { getQueuedMove() } returns BASIC_PHYSICAL_ATTACK
            every { getQueuedTargets() } returns arrayOf(enemy1, enemy2)
        }
        mockkObject(SeedManager)


        every { SeedManager.getDouble(0.0, 0.9) } returnsMany listOf(.1, .2, .3, .4, .5, .6, .7, .8)

        runTest { onRenderingThread { subject.onActionPhase() } }

        verifyOrder {
            actionManager.initiateActiveAbility(symphonist1, BASIC_PHYSICAL_ATTACK, enemy1, enemy2)
            actionManager.initiateActiveAbility(symphonist2, BASIC_PHYSICAL_ATTACK, enemy2)
            actionManager.initiateActiveAbility(enemy1, BASIC_PHYSICAL_ATTACK, symphonist1)
            actionManager.initiateActiveAbility(enemy2, BASIC_PHYSICAL_ATTACK, symphonist2)
        }
    }

    //endregion onActionPhase

    @After
    fun cleanup() {
        unmockkAll()
    }
}