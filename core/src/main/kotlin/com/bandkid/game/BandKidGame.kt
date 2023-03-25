package com.bandkid.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.bandkid.game.ui.screens.LoadingScreen
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.inject.Context
import ktx.inject.register
import ktx.scene2d.Scene2DSkin
import ktx.style.label

class BandKidGame : KtxGame<KtxScreen>() {

    private val countdownFont: BitmapFont by lazy { FreeTypeFontGenerator(Gdx.files.internal(COUNTDOWN_FONT)).let {
        it.generateFont(FreeTypeFontGenerator.FreeTypeFontParameter().apply { size = 20 })
            .apply { it.dispose() }
    } }
    private val context = Context()


    override fun create() {
        //Scene2DSkin.defaultSkin = makeSkin()//TODO MAKE CUSTOM SKIN
        context.register {
            bindSingleton<Batch>(SpriteBatch())
            bindSingleton(countdownFont)
            bindSingleton(AssetManager())
            //bindSingleton(Skin())
            bindSingleton(OrthographicCamera().apply { setToOrtho(false, 800f, 480f) })

            addScreen(LoadingScreen(this@BandKidGame, inject(), inject(), inject(), inject()))
        }
        setScreen<LoadingScreen>()
        super.create()
    }

    override fun dispose() {
        countdownFont.dispose()
        context.dispose()
        super.dispose()
    }

    private fun makeSkin(): Skin {
        return Skin().apply {
            label { this.font = countdownFont }
        }
    }


    companion object {
        const val COUNTDOWN_FONT = "countdown.ttf"
    }
}
