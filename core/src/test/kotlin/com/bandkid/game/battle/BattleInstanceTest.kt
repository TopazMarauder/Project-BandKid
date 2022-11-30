package com.bandkid.game.battle

import com.bandkid.game.AsyncTest
import com.bandkid.game.creatures.models.enemies.Enemy
import com.bandkid.game.creatures.models.symphonists.Symphonist
import com.bandkid.game.player.PlayerProvider
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import ktx.async.KtxAsync
import ktx.async.MainDispatcher
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BattleInstanceTest: AsyncTest() {
    private val playerProvider = mockk<PlayerProvider>()
    private val enemyProvider = mockk<EnemyProvider>()

    //COROUTINE VERSION
    @Test
    fun onCreate_callsOnPassivePhase() {
        runTest {
            val testDispatcher = UnconfinedTestDispatcher(testScheduler)
            Dispatchers.setMain(testDispatcher)

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
    }



}