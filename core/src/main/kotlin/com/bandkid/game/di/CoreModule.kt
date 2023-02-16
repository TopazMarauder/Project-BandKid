package com.bandkid.game.di

import com.bandkid.game.BandKidGame
import com.bandkid.game.models.Coordinates
import com.bandkid.game.models.Party
import com.bandkid.game.player.PlayerData
import com.bandkid.game.player.PlayerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    fun game() : BandKidGame {
        return BandKidGame()
    }
}