package com.bandkid.game.battle

import com.bandkid.game.AsyncTest
import com.bandkid.game.battle.activeabilities.AbilityName
import com.bandkid.game.battle.activeabilities.AbilityName.BASIC_PHYSICAL_ATTACK
import com.bandkid.game.creatures.models.enemies.Enemy
import com.bandkid.game.creatures.models.symphonists.Symphonist
import com.bandkid.game.player.PlayerProvider
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BattleInstanceTest: AsyncTest() {
    private val playerProvider = mockk<PlayerProvider>()
    private val enemyProvider = mockk<EnemyProvider>()
    private val actionManager = mockk<ActionManager>(relaxed = true)


    //region onChoicePhase
    @Test
    fun onChoicePhase_getsEnemyMoves() {
            val orchestra = mockk<MutableList<Symphonist>>(relaxed = true)
            val enemy1 = mockk<Enemy>(relaxed = true)
            val enemy2 = mockk<Enemy>(relaxed = true)
            val enemies = mutableListOf<Enemy>(enemy1, enemy2)
            every { playerProvider.getOrchestra() } returns orchestra
            every { enemyProvider.getEnemies() } returns enemies

            val subject = BattleInstance()
            subject.playerProvider = playerProvider
            subject.enemyProvider = enemyProvider

            subject.onCreate()

            verify { enemy1.queueMove(orchestra, enemies) }
            verify { enemy2.queueMove(orchestra, enemies) }
    }
    //endregion onChoicePhase

    //region onActionPhase
    @Test
    fun onActionPhase_givenOffensiveActions_callsInitiateOffensiveActiveAbilityOrderedByAgility() {
        val symphonist1 = mockk<Symphonist>{ every { agility } returns 1 }
        val symphonist2 = mockk<Symphonist>{ every { agility } returns 3 }
        val enemy1 = mockk<Enemy>{ every { agility } returns 2 }
        val enemy2 = mockk<Enemy>{ every { agility } returns 4 }
        val orchestra = mutableListOf(symphonist1, symphonist2)
        val enemies = mutableListOf(enemy1, enemy2)
        every { playerProvider.getOrchestra() } returns orchestra
        every { enemyProvider.getEnemies() } returns enemies
        every { enemy2.moveInQueue } returns Pair(listOf(symphonist2), BASIC_PHYSICAL_ATTACK)
        every { symphonist2.moveInQueue } returns Pair(listOf(enemy2), BASIC_PHYSICAL_ATTACK)
        every { enemy1.moveInQueue } returns Pair(listOf(symphonist1), BASIC_PHYSICAL_ATTACK)
        every { symphonist1.moveInQueue } returns Pair(listOf(enemy1, enemy2), BASIC_PHYSICAL_ATTACK)


        val subject = BattleInstance()
        subject.playerProvider = playerProvider
        subject.enemyProvider = enemyProvider
        subject.actionManager = actionManager


        verifyOrder {
            subject.actionManager.initiateOffensiveActiveAbility(enemy2, BASIC_PHYSICAL_ATTACK, symphonist2)
            subject.actionManager.initiateOffensiveActiveAbility(symphonist2, BASIC_PHYSICAL_ATTACK,  enemy2)
            subject.actionManager.initiateOffensiveActiveAbility(enemy1, BASIC_PHYSICAL_ATTACK, symphonist1)
            subject.actionManager.initiateOffensiveActiveAbility(symphonist1, BASIC_PHYSICAL_ATTACK, enemy1, enemy2)
        }
    }

    //endregion onActionPhase

}