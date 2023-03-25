package com.bandkid.game.ui.screens.menus

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.TextInputListener
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.utils.ScreenUtils
import com.bandkid.game.BandKidGame
import com.bandkid.game.battle.BattleScreen
import com.bandkid.game.utils.SeedManager
import ktx.app.KtxScreen
import ktx.async.skipFrame
import ktx.graphics.use

class MainMenuScreen  (private val game: BandKidGame,
                       private val batch: Batch,
                       private val font: BitmapFont,
                       private val assets: AssetManager,
                       private val camera: OrthographicCamera) : KtxScreen {

    override fun show() {}

    override fun render(delta: Float) {

        ScreenUtils.clear(0f,0f,0.2f,1f)

        camera.update()

            batch.projectionMatrix = camera.combined
            batch.use {
                font.draw(batch, "Brass and Blood", 100f, 150f)
                font.draw(batch, SeedManager.getSeed().toString(), 50f, 250f)
                //font.draw()
            }



            if (Gdx.input.justTouched()){
                game.addScreen(BattleScreen(game, batch, font, assets, camera))
                game.setScreen<BattleScreen>()
                game.removeScreen<MainMenuScreen>()
                dispose()
            }


    }

    override fun resize(width: Int, height: Int) {}
    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
    override fun dispose() {}

}
