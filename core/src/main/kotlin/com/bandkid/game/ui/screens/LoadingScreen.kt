package com.bandkid.game.ui.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.bandkid.game.BandKidGame
import com.bandkid.game.ui.screens.menus.MainMenuScreen
import com.bandkid.game.utils.*
import ktx.app.KtxScreen
import ktx.graphics.use
import ktx.scene2d.*

class LoadingScreen (private val game: BandKidGame,
                     private val batch: Batch,
                     private val font: BitmapFont,
                     private val assets: AssetManager,
                     private val camera: OrthographicCamera) : KtxScreen {




    override fun show() {
        MusicAssets.values().forEach { assets.load(it) }
        SoundAssets.values().forEach { assets.load(it) }
        TextureAtlasAssets.values().forEach { assets.load(it) }
    }

    override fun render(delta: Float) {
        //continue loading assets
        assets.update()
        camera.update()

            batch.projectionMatrix = camera.combined
            batch.use {
                font.draw(it, "Brass and Blood", 100f, 150f)
                if (assets.isFinished) {
                    font.draw(it, "Finished Loading", 100f, 100f)
                    //font.draw(it, "Enter Seed:_______________", 50f, 250f)
                    //scene2d.label(text = "Enter Seed:_______________")//.apply {
                        //x = 50f
                        //y = 250f
                       //onClick {

                        //}
                        //draw(batch, 100f)
                   // }
                    //font.draw(it, "", 275f, 250f)

                } else {
                    font.draw(it, "Loading Assets. . . ", 100f, 100f)
                }
            }


            if (Gdx.input.justTouched() && assets.isFinished){
                game.addScreen(MainMenuScreen(game, batch, font, assets, camera))
                game.setScreen<MainMenuScreen>()
                game.removeScreen<LoadingScreen>()
                dispose()
            }
        }


    override fun resize(width: Int, height: Int) {}
    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
    override fun dispose() {}

}