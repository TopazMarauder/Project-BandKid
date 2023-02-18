package com.bandkid.game.menus

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.utils.ScreenUtils
import com.bandkid.game.BandKidGame
import com.bandkid.game.battle.BattleScreen
import com.bandkid.game.utils.*
import ktx.app.KtxScreen
import ktx.graphics.use

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
                    font.draw(it, SeedManager.getSeed().toString(), 50f, 250f)
                } else {
                    font.draw(it, "Loading Assets. . . ", 100f, 100f)
                }
            }



            if (Gdx.input.isTouched && assets.isFinished){
                game.addScreen(MainMenuScreen(game, batch, font, assets, camera))
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