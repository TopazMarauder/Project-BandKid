package com.bandkid.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter.Linear
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.bandkid.game.di.CoreComponent
import com.bandkid.game.di.CoreModule
import com.bandkid.game.menus.MainMenuScreen
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.graphics.use

class BandKidGame : KtxGame<KtxScreen>() {

    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont

    lateinit var coreComponent: CoreComponent

    override fun create() {

        batch = SpriteBatch()
        font = FreeTypeFontGenerator(Gdx.files.internal(COUNTDOWN_FONT)).let {
            it.generateFont(FreeTypeFontGenerator.FreeTypeFontParameter().apply { size = 20 })
                .apply { it.dispose() }
        }
        addScreen(MainMenuScreen(this))
        addScreen(FirstScreen())
        setScreen<MainMenuScreen>()
    }

    companion object {
        const val COUNTDOWN_FONT = "countdown.ttf"
    }
}

class FirstScreen : KtxScreen {
    private val image = Texture("logo.png".toInternalFile(), true).apply { setFilter(Linear, Linear) }
    private val batch = SpriteBatch()

    override fun render(delta: Float) {
        clearScreen(red = 0.7f, green = 0.7f, blue = 0.7f)
        batch.use {
            it.draw(image, 100f, 160f)
        }
    }

    override fun dispose() {
        image.disposeSafely()
        batch.disposeSafely()
    }
}
