package com.bandkid.game.di

import com.bandkid.game.BandKidGame
import com.bandkid.game.menus.MainMenuScreen
import com.bandkid.game.player.PlayerProvider
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {
    fun game(): BandKidGame

    @Component.Factory
    interface Factory {
        fun create(): CoreComponent
    }

}