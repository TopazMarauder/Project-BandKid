package com.bandkid.game.menus

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.ScreenUtils
import com.bandkid.game.examples.ExampleGameScreen
import com.bandkid.game.examples.ExampleTwo

class MainMenuScreen(val game: ExampleTwo) : Screen {
    private var orthographicCamera: OrthographicCamera = OrthographicCamera().apply { setToOrtho(false, 800f, 400f) }

    override fun show() {}

    override fun render(delta: Float) {
        ScreenUtils.clear(0f,0f,0.2f,1f)

        orthographicCamera.update()

        with(game){
            batch.projectionMatrix.set(orthographicCamera.combined)
            batch.begin()
            font.draw(batch, "Brass and Blood", 100f, 150f)
            font.draw(batch, "Tap to begin", 100f, 100f)
            batch.end()

            if (Gdx.input.isTouched){
                screen = ExampleGameScreen(game)
            }
        }
        dispose()
    }

    override fun resize(width: Int, height: Int) {}
    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
    override fun dispose() {}

}
