package com.bandkid.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.FitViewport
import com.bandkid.game.ui.screens.LoadingScreen
import ktx.actors.stage
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.inject.Context
import ktx.inject.register
import ktx.log.debug
import ktx.scene2d.Scene2DSkin
import ktx.style.label
import ktx.style.textButton
import ktx.style.textField
import ktx.style.touchpad

class BandKidGame : KtxGame<KtxScreen>() {

    private lateinit var batch: SpriteBatch
    private lateinit var stage: Stage

    private val countdownFont: BitmapFont by lazy { FreeTypeFontGenerator(Gdx.files.internal(COUNTDOWN_FONT)).let {
        it.generateFont(FreeTypeFontGenerator.FreeTypeFontParameter().apply { size = 20 })
            .apply { it.dispose() }
    } }
    private val context = Context()


    override fun create() {
        setupBatchAndStage()
        Scene2DSkin.defaultSkin = makeSkin()//TODO MAKE CUSTOM SKIN
        Gdx.input.inputProcessor = stage
        context.register {
            bindSingleton<Batch>(batch)
            bindSingleton(countdownFont)
            bindSingleton(AssetManager())
            bindSingleton(stage)
            bindSingleton(OrthographicCamera().apply { setToOrtho(false, 800f, 480f) })

            addScreen(LoadingScreen(this@BandKidGame, inject(), inject(), inject(), inject(), inject()))
        }
        setScreen<LoadingScreen>()
        super.create()
    }

    private fun setupBatchAndStage() {
        batch = SpriteBatch()
        stage = stage(batch = batch, FitViewport(800f, 480f))
    }

    override fun dispose() {
        countdownFont.dispose()
        context.dispose()
        super.dispose()
    }

    private fun makeSkin(): Skin {
        return Skin().apply {
            label { this.font = countdownFont }
            textField { this.font = countdownFont
            fontColor = Color.CYAN}
            touchpad { }
            textButton { this.font = countdownFont }
                   }
    }


    companion object {
        const val COUNTDOWN_FONT = "countdown.ttf"
    }
}
