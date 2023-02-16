package com.bandkid.game.menus

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.ScreenUtils
import com.bandkid.game.BandKidGame
import com.bandkid.game.utils.MusicAssets
import com.bandkid.game.utils.SoundAssets
import com.bandkid.game.utils.TextureAtlasAssets
import com.bandkid.game.utils.load
import ktx.app.KtxScreen
import ktx.graphics.use

class LoadingScreen (val game: BandKidGame) : KtxScreen {

    private var orthographicCamera: OrthographicCamera = OrthographicCamera().apply { setToOrtho(false, 800f, 400f) }



    override fun show() {
        MusicAssets.values().forEach { game.assets.load(it) }
        SoundAssets.values().forEach { game.assets.load(it) }
        TextureAtlasAssets.values().forEach { game.assets.load(it) }
    }

    override fun render(delta: Float) {
        //continue loading assets
        game.assets.update()


        orthographicCamera.update()

        with(game){
            batch.projectionMatrix = orthographicCamera.combined
            batch.use {
                font.draw(it, "Brass and Blood", 100f, 150f)
                if (game.assets.isFinished) {
                    font.draw(it, com.bandkid.game.utils.SeedManager.getSeed().toString(), 50f, 250f)
                } else{
                    font.draw(it, "Loading Assets. . . ", 100f, 100f)
                }

                //font.draw()
            }



            if (com.badlogic.gdx.Gdx.input.isTouched && game.assets.isFinished){
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