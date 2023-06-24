package com.bandkid.game.ui.screens.huds

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.bandkid.game.ui.widgets.ResourcesBarWidget
import com.bandkid.game.ui.widgets.resourcesBarWidget
import ktx.actors.centerPosition
import ktx.scene2d.KTable
import ktx.scene2d.Scene2DSkin
import ktx.scene2d.horizontalGroup
import ktx.scene2d.label
import ktx.scene2d.table
import ktx.scene2d.verticalGroup

class BattleHud(skin: Skin = Scene2DSkin.defaultSkin): Table(skin), KTable {

    val resourcesBar: ResourcesBarWidget

    init {
        resourcesBar = resourcesBarWidget {
            it.top()
            it.left()
            debug()
            it.growX()
        }
        row()

        table(){tableCell ->
            tableCell.fill()
            tableCell.grow()
            debug()
            verticalGroup{
                label("a")
                it.growY()
            }
            verticalGroup() {
                debug()
                label("b")
                it.grow()
            }.apply {
                grow()
            }

            verticalGroup{
                debug()
                label("c")
                it.growY()
            }
        }
        row()
        horizontalGroup(){
            debug()
            label("test"){
            }.apply {
                it.growX()
            }
        }


        debug()


    }
}

//horizontalGroup(){
//                    label("enemiesHere!").setFontScale(.45f)
//                    label("enemiesHere!").setFontScale(.45f)
//                    label("enemiesHere!").setFontScale(.45f)
//                    label("enemiesHere!").setFontScale(.45f)
//                    label("enemiesHere!").setFontScale(.45f)
//                    top()
//                }.apply {
//                    grow()
//                }
//                horizontalGroup(){
//                    label("playerUnitsHere!").setFontScale(.45f)
//                    label("playerUnitsHere!").setFontScale(.45f)
//                    label("playerUnitsHere!").setFontScale(.45f)
//                    label("playerUnitsHere!").setFontScale(.45f)
//                    bottom()
//                }.apply {
//                    grow()
//                }