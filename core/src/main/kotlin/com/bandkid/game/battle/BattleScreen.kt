package com.bandkid.game.battle

import com.bandkid.game.BandKidGame
import com.bandkid.game.utils.MusicAssets
import com.bandkid.game.utils.SoundAssets
import com.bandkid.game.utils.TextureAtlasAssets
import com.bandkid.game.utils.get
import ktx.app.KtxScreen

class BattleScreen(val game: BandKidGame) : KtxScreen {
    private val dropImage = game.assets[TextureAtlasAssets.Game].findRegion("drop")
    private val rainMusic = game.assets[MusicAssets.Rain].apply { isLooping = true }


}