package com.bandkid.game.ui.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Stage
import com.bandkid.game.BandKidGame
import com.bandkid.game.ui.screens.menus.MainMenuScreen
import com.bandkid.game.utils.*
import ktx.actors.onClick
import ktx.app.KtxScreen
import ktx.graphics.use
import ktx.inject.Context
import ktx.scene2d.actors
import ktx.scene2d.label
import ktx.scene2d.scene2d
import ktx.scene2d.textField

class LoadingScreen (private val game: BandKidGame,
                     private val batch: Batch,
                     private val font: BitmapFont,
                     private val assets: AssetManager,
                     private val camera: OrthographicCamera,
                     private val stage: Stage
) : BaseScreen() {
    private val seedField = scene2d.textField(text = "_______________").apply {
        x = 250f
        y = 250f
        sizeBy(120f)
        onClick { text = "" }
    }


    override fun show() {
        MusicAssets.values().forEach { assets.load(it) }
        SoundAssets.values().forEach { assets.load(it) }
        TextureAtlasAssets.values().forEach { assets.load(it) }
        stage.actors {
            label("Enter Seed"){
                x = 40f
                y = 250f
                sizeBy(120f)
            }
        }

    }

    override fun render(delta: Float) {
        //continue loading assets
        assets.update()
        camera.update()
        stage.addActor(seedField)

            batch.projectionMatrix = camera.combined
            batch.use {
                stage
                font.draw(it, "Brass and Blood", 100f, 150f)
                if (assets.isFinished) {
                    font.draw(it, "Finished Loading", 100f, 100f)
                    //font.draw(it, "Enter Seed:_______________", 50f, 250f)
                    font.draw(it, "", 275f, 250f)

                } else {
                    font.draw(it, "Loading Assets. . . ", 100f, 100f)
                }
            }
        stage.act()
        stage.draw()


//            if (Gdx.input.justTouched() && assets.isFinished){
//                game.addScreen(MainMenuScreen(game, batch, font, assets, camera))
//                game.setScreen<MainMenuScreen>()
//                game.removeScreen<LoadingScreen>()
//                dispose()
//            }
        }


    override fun resize(width: Int, height: Int) {}
    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
    override fun dispose() {}

}