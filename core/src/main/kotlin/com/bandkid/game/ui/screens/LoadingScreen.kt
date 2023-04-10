package com.bandkid.game.ui.screens

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter.DigitsOnlyFilter
import com.bandkid.game.BandKidGame
import com.bandkid.game.ui.widgets.SeedWidget
import com.bandkid.game.utils.*
import ktx.actors.onClick
import ktx.graphics.use
import ktx.scene2d.*

class LoadingScreen (private val game: BandKidGame,
                     private val batch: Batch,
                     private val font: BitmapFont,
                     private val assets: AssetManager,
                     private val camera: OrthographicCamera,
                     private val stage: Stage
) : BaseScreen() {
    val seedWidget = SeedWidget()

    override fun show() {
        MusicAssets.values().forEach { assets.load(it) }
        SoundAssets.values().forEach { assets.load(it) }
        TextureAtlasAssets.values().forEach { assets.load(it) }
    }


    private val startButton = scene2d.textButton("ENTER"){
        x = 250f
        y = 100f
        debug()
        onClick {

            }
        }


    override fun render(delta: Float) {
        //continue loading assets
        assets.update()
        camera.update()


        batch.projectionMatrix = camera.combined
        batch.use {
            stage.addActor(seedWidget)
            seedWidget.apply {
                seedEnterButton.onClick { applySeedFromField() }
                x = 20f
                y = 400f

            }
            stage.addActor(startButton)
            font.draw(it, "Brass and Blood", 100f, 150f)
            if (assets.isFinished) {
                font.draw(it, "Finished Loading", 100f, 100f)
                //font.draw(it, "Enter Seed:_______________", 50f, 250f)
                //font.draw(it, "", 275f, 250f)

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


    private fun applySeedFromField() {
        with(seedWidget){
        if (seedField.text == "_______________" || seedField.text == "") {
            seedField.text = SeedManager.getSeed().toString()
        } else {
            seedField.text = SeedManager.getSeed(seedField.text.toInt()).toString()
        }
    }
    }


    override fun resize(width: Int, height: Int) {}
    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
    override fun dispose() {}

}