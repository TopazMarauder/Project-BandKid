package com.bandkid.game.ui.screens.huds

import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.bandkid.game.ui.widgets.ResourcesBarWidget
import com.bandkid.game.ui.widgets.resourcesBarWidget
import ktx.scene2d.KTable
import ktx.scene2d.Scene2DSkin

class BattleHud(skin: Skin = Scene2DSkin.defaultSkin): Table(skin), KTable {

    val resourcesBar: ResourcesBarWidget

    init {
        resourcesBar = resourcesBarWidget {
            it.colspan(3)
        }

        debug()


    }
}