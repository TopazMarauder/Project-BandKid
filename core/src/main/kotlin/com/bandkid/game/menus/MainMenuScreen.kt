package com.bandkid.game.menus

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.ScreenUtils
import com.bandkid.game.BandKidGame
import com.bandkid.game.player.PlayerProvider
import ktx.app.KtxScreen
import javax.inject.Inject

class MainMenuScreen  (val game: BandKidGame) : KtxScreen {
    private var orthographicCamera: OrthographicCamera = OrthographicCamera().apply { setToOrtho(false, 800f, 400f) }

    @Inject
    lateinit var playerProvider: PlayerProvider


    override fun show() {}

    override fun render(delta: Float) {
        ScreenUtils.clear(0f,0f,0.2f,1f)

        orthographicCamera.update()

        with(game){
            batch.projectionMatrix.set(orthographicCamera.combined)
            batch.begin()
            font.draw(batch, "Brass and Blood", 100f, 150f)
            font.draw(batch, playerProvider.getPartySize().first.toString(), 100f, 100f)
            batch.end()

        }
        dispose()
    }

    override fun resize(width: Int, height: Int) {}
    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
    override fun dispose() {}

}
