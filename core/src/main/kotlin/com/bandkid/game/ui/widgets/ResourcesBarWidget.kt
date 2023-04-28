package com.bandkid.game.ui.widgets

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup
import ktx.scene2d.KGroup
import ktx.scene2d.KWidget
import ktx.scene2d.Scene2dDsl
import ktx.scene2d.actor
import ktx.scene2d.label

@Scene2dDsl
class ResourcesBarWidget: HorizontalGroup(), KGroup {

    init{
        label("Resources Bar")
    }
}

@Scene2dDsl
inline fun <S> KWidget<S>.resourcesBarWidget(
    init: ResourcesBarWidget.(S) -> Unit = {}
): ResourcesBarWidget = actor(ResourcesBarWidget(), init)