package com.bandkid.game.menus

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.TextInputListener
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.ScreenUtils
import com.bandkid.game.BandKidGame
import com.bandkid.game.utils.SeedManager
import ktx.app.KtxScreen
import ktx.graphics.use

class MainMenuScreen  (val game: BandKidGame) : KtxScreen {
    private var orthographicCamera: OrthographicCamera = OrthographicCamera().apply { setToOrtho(false, 800f, 400f) }



    override fun show() {}

    override fun render(delta: Float) {
        ScreenUtils.clear(0f,0f,0.2f,1f)

        orthographicCamera.update()

        with(game){
            batch.projectionMatrix = orthographicCamera.combined
            batch.use {
                font.draw(batch, "Brass and Blood", 100f, 150f)
                font.draw(batch, SeedManager.getSeed().toString(), 50f, 250f)
                //font.draw()
            }



            if (Gdx.input.isTouched){
                //addScreen()
                game.removeScreen<MainMenuScreen>()
                dispose()
            }
        }



    }

    override fun resize(width: Int, height: Int) {}
    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
    override fun dispose() {}

}
