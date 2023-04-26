package com.bandkid.game.ui.screens

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Stage
import com.bandkid.game.BandKidGame
import com.bandkid.game.ui.screens.huds.MainMenuHUD
import ktx.actors.centerPosition
import ktx.actors.onClick
import ktx.graphics.use

class MainMenuScreen(
    private val game: BandKidGame,
    private val batch: Batch,
    private val font: BitmapFont,
    private val assets: AssetManager,
    private val camera: OrthographicCamera,
    private val baseStage: Stage
) : BaseScreen() {

    private val mainMenuHUD = MainMenuHUD()

    override fun show() {
        super.show()
        baseStage.clear()
    }

    override fun render(delta: Float) {
        super.render(delta)

        baseStage.addActor(mainMenuHUD)

        mainMenuHUD.apply {
            setSize(stage.width-100f, stage.height-100f)
            centerPosition(width+100f, height+100f)
            //if (assets.isFinished) loadStatusText.setText()
            startButton.onClick {
                game.addScreen(BattleScreen(game, batch, font, assets, camera, baseStage))
                game.setScreen<BattleScreen>()
                game.removeScreen<MainMenuScreen>()
                dispose()
            }
        }

        baseStage.viewport.apply()
        baseStage.act()
        baseStage.draw()
    }


    override fun hide() {
        baseStage.clear()
    }


}
