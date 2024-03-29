package com.bandkid.game.utils

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import ktx.assets.getAsset
import ktx.assets.load

//region sounds
enum class SoundAssets(val path: String) {
}

inline fun AssetManager.load(asset: SoundAssets) = load<Sound>(asset.path)
inline operator fun AssetManager.get(asset: SoundAssets) = getAsset<Sound>(asset.path)
//endregion sounds

//region music
enum class MusicAssets(val path: String) {
    Base("music/base.ogg")
}

inline fun AssetManager.load(asset: MusicAssets) = load<Music>(asset.path)
inline operator fun AssetManager.get(asset: MusicAssets) = getAsset<Music>(asset.path)
//endregion music

//region texture atlas
enum class TextureAtlasAssets(val path: String) {
    Game("images/game.atlas"),
    UI("images/game.atlas")
}

inline fun AssetManager.load(asset: TextureAtlasAssets) = load<TextureAtlas>(asset.path)
inline operator fun AssetManager.get(asset: TextureAtlasAssets) = getAsset<TextureAtlas>(asset.path)
//endregion texture atlas