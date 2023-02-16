@file:JvmName("Lwjgl3Launcher")

package com.bandkid.game.lwjgl3

import com.badlogic.gdx.Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.bandkid.game.BandKidGame

/** Launches the desktop (LWJGL3) application. */
fun main() {
    Lwjgl3Application(BandKidGame.createGame(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("Project-BandKid")
        setWindowedMode(640, 480)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
    }).logLevel = Application.LOG_DEBUG
}
