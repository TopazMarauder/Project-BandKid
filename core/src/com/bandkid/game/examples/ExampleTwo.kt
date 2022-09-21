package com.bandkid.game.examples

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.bandkid.game.menus.MainMenuScreen

class ExampleTwo: Game() {

    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont

    override fun create() {
        batch = SpriteBatch()
        font = FreeTypeFontGenerator(Gdx.files.internal(Example.COUNTDOWN_FONT)).let {
            it.generateFont(FreeTypeFontParameter().apply { size = 20 }).apply { it.dispose() }
        }
        this.setScreen( MainMenuScreen(this))
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {
        this.getScreen().dispose()

        batch.dispose()
        font.dispose()
    }
}