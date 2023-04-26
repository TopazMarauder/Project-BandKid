package com.bandkid.game.ui.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.ScreenUtils
import com.bandkid.game.BandKidGame
import com.bandkid.game.utils.*
import ktx.actors.centerPosition
import ktx.actors.stage
import ktx.graphics.use
import ktx.scene2d.*

class LoadingScreen (private val game: BandKidGame,
                     private val batch: Batch,
                     private val font: BitmapFont,
                     private val assets: AssetManager,
                     private val camera: OrthographicCamera,
                     private val baseStage: Stage
) : BaseScreen() {

    private val loadingStatus = Label("Loading Assets. . . ", Scene2DSkin.defaultSkin).apply{
        if (assets.isFinished){
            setText("Finished Loading")
        }
    }


    override fun show() {
        baseStage.clear()
        MusicAssets.values().forEach { assets.load(it) }
        SoundAssets.values().forEach { assets.load(it) }
        TextureAtlasAssets.values().forEach { assets.load(it) }

        baseStage.actors {
            label("Brass and Blood"){
                centerPosition(height = stage.height-80f)
            }
        }
        baseStage.addActor(loadingStatus)
        loadingStatus.centerPosition()
    }


    override fun render(delta: Float) {

        super.render(delta)
        //continue loading assets
        assets.update()
        camera.update()


        batch.projectionMatrix = camera.combined
        batch.use {
        }
        baseStage.viewport.apply()
        baseStage.act()
        baseStage.draw()


            if (Gdx.input.justTouched() && assets.isFinished){
                game.addScreen(MainMenuScreen(game, batch, font, assets, camera, baseStage))
                game.setScreen<MainMenuScreen>()
                game.removeScreen<LoadingScreen>()
                dispose()
            }
    }

    override fun hide() {
        baseStage.clear()
    }






}