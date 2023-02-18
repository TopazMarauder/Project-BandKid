package com.bandkid.game.battle

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.bandkid.game.BandKidGame
import com.bandkid.game.utils.MusicAssets
import com.bandkid.game.utils.SoundAssets
import com.bandkid.game.utils.TextureAtlasAssets
import com.bandkid.game.utils.get
import ktx.app.KtxScreen

class BattleScreen(private val game: BandKidGame,
                   private val batch: Batch,
                   private val font: BitmapFont,
                   private val assets: AssetManager,
                   private val camera: OrthographicCamera) : KtxScreen {
    private val dropImage = assets[TextureAtlasAssets.Game].findRegion("drop")
    private val rainMusic = assets[MusicAssets.Base].apply { isLooping = true }


}