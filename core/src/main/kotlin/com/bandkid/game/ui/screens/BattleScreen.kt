package com.bandkid.game.ui.screens

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Stage
import com.bandkid.game.BandKidGame
import com.bandkid.game.player.PlayerProvider
import com.bandkid.game.utils.MusicAssets
import com.bandkid.game.utils.SeedManager
import com.bandkid.game.utils.TextureAtlasAssets
import com.bandkid.game.utils.get
import ktx.actors.centerPosition
import ktx.app.KtxScreen
import ktx.scene2d.actors
import ktx.scene2d.label

class BattleScreen(private val game: BandKidGame,
                   private val batch: Batch,
                   private val font: BitmapFont,
                   private val assets: AssetManager,
                   private val camera: OrthographicCamera,
                   private val baseStage: Stage
                   ) : KtxScreen {
    private val dropImage = assets[TextureAtlasAssets.Game].findRegion("drop")
    private val rainMusic = assets[MusicAssets.Base].apply { isLooping = true }

    init {
        PlayerProvider

    }

    override fun show() {
        baseStage.clear()
        baseStage.actors {
            label(SeedManager.getSeed().toString()){
                centerPosition()
            }
        }

    }

    override fun render(delta: Float) {

        super.render(delta)




        baseStage.viewport.apply()
        baseStage.act()
        baseStage.draw()
    }


}