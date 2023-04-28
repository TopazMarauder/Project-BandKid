package com.bandkid.game.ui.screens

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Stage
import com.bandkid.game.BandKidGame
import com.bandkid.game.battle.EnemyProvider
import com.bandkid.game.battle.abilities.AbilityName
import com.bandkid.game.creatures.models.enemies.BrassLegionnaire
import com.bandkid.game.creatures.models.enemies.BrassetHound
import com.bandkid.game.creatures.models.symphonists.Symphonist
import com.bandkid.game.player.PlayerProvider
import com.bandkid.game.ui.screens.huds.BattleHud
import com.bandkid.game.utils.MusicAssets
import com.bandkid.game.utils.SeedManager
import com.bandkid.game.utils.TextureAtlasAssets
import com.bandkid.game.utils.get
import ktx.actors.centerPosition
import ktx.app.KtxScreen
import ktx.scene2d.actors
import ktx.scene2d.label
import kotlin.random.Random

class BattleScreen(private val game: BandKidGame,
                   private val batch: Batch,
                   private val font: BitmapFont,
                   private val assets: AssetManager,
                   private val camera: OrthographicCamera,
                   private val baseStage: Stage
                   ) : KtxScreen {
    private val dropImage = assets[TextureAtlasAssets.Game].findRegion("drop")
    private val rainMusic = assets[MusicAssets.Base].apply { isLooping = true }

    private val battleHud = BattleHud()

    init {
        val symphonist1 = object: Symphonist(1, 1, 1, 1, SeedManager.getInt(10,100), 100, 100, moveSet = mutableListOf(
            AbilityName.BASIC_PHYSICAL_ATTACK)) {}

        val legionnaire1 = BrassLegionnaire()
        val legionnaire2 = BrassLegionnaire()
        val legionnaire3 = BrassLegionnaire()
        PlayerProvider.setOrchestra(mutableListOf(symphonist1))
        EnemyProvider.setCacophony(mutableListOf(legionnaire1, legionnaire2, legionnaire3))

    }

    override fun show() {
        super.show()
        baseStage.clear()
        baseStage.actors {
            label(SeedManager.getSeed().toString()){
                centerPosition()
            }

            label(PlayerProvider.getOrchestra()[0].agility.toString()){
                centerPosition(height = baseStage.height-40f)
            }
        }

    }

    override fun render(delta: Float) {
        super.render(delta)

        baseStage.addActor(battleHud)

        battleHud.apply {
            setFillParent(true)
            centerPosition()
        }

        baseStage.viewport.apply()
        baseStage.act()
        baseStage.draw()
    }


}