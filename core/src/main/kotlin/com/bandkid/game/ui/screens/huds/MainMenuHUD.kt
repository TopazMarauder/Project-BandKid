package com.bandkid.game.ui.screens.huds

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Cell
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.ui.Window
import com.bandkid.game.ui.widgets.SeedWidget
import com.bandkid.game.utils.SeedManager
import ktx.actors.onClick
import ktx.scene2d.*

class MainMenuHUD(skin: Skin = Scene2DSkin.defaultSkin): Table(skin), KTable {

    private val enterLabel: Label

    val seedField: TextField

    val seedEnterButton: TextButton

    val startButton: TextButton


    init {
        debug()
//        setPosition(0f, 100f)
//        defaults().pad(5f, 5f, 5f, 5f)

        enterLabel = label("Enter Seed"){
            debug()
        }

        seedField = textField( "_________________").apply {
            textFieldFilter = TextField.TextFieldFilter.DigitsOnlyFilter()
            maxLength = 9
            onClick { text = "" }
            debug()
        }.cell(minWidth = 100f, growX = true)

        seedEnterButton = textButton("ENTER"){
            debug()
            onClick { this@MainMenuHUD.applySeedFromField() }
        }
        row()

        startButton = textButton("ENTER"){
            debug()
            onClick { this@MainMenuHUD.applySeedFromField() }
            it.prefWidth(100f)
            it.colspan(3)
        }



    }


    private fun applySeedFromField() {
            if (seedField.text == "_________________" || seedField.text == "") {
                seedField.text = SeedManager.getSeed().toString()
            } else {
                seedField.text = SeedManager.getSeed(seedField.text.toInt()).toString()
            }
    }
}