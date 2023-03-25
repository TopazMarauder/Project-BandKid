package com.bandkid.game.player.models

interface Item {
//TODO basic item properties?
    val strengthModifier: Int
    val durabilityModifier: Int
    val intellectModifier: Int
    val constitutionModifier: Int
    val agilityModifier: Int
    var maxHealthPointsModifier: Int
    var currentHealthPointsModifier: Int
    var shieldPointsModifier: Int
    var isRagedModifier: Boolean?
    var isCrippledModifier: Boolean?
}
