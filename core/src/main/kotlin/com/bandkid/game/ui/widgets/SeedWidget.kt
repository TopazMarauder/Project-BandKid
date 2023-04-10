package com.bandkid.game.ui.widgets

import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.bandkid.game.utils.SeedManager
import ktx.actors.onClick
import ktx.scene2d.*

class SeedWidget(skin: Skin = Scene2DSkin.defaultSkin): Table(skin), KTable {

    private val enterLabel = label("Enter Seed"){
        debug()
    }

    val seedField = textField(text = "_______________").apply {
        textFieldFilter = TextField.TextFieldFilter.DigitsOnlyFilter()
        onClick { text = "" }
        debug()
    }

    val seedEnterButton = textButton("ENTER"){
        debug()
    }



    init {
        table{}
        add(enterLabel)
        add(seedField).expandX()
        add(seedEnterButton)
    }





}