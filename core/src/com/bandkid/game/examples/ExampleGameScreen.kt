package com.bandkid.game.examples

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.TimeUtils

class ExampleGameScreen(val game: ExampleTwo) : Screen {

    private var dropImage: Texture = Texture(DROP_IMG)
    private var bucketImage: Texture = Texture(BUCKET_IMG)
    private var rainMusic: Music = Gdx.audio.newMusic(Gdx.files.internal(RAIN_SOUND))
    private var dropSound: Sound = Gdx.audio.newSound(Gdx.files.internal(DROP_SOUND))

    private var orthographicCamera: OrthographicCamera = OrthographicCamera().apply { setToOrtho(false, 800f, 400f) }

    private var bucket: Rectangle = Rectangle().apply {
        x = 800f / 2f - 64f / 2f
        y = 20f
        width = 64f
        height = 64f
    }
    private var rainDrops: Array<Rectangle> = Array()
    private var lastDrop: Long? = null

    override fun show() {
        rainMusic.play()
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(0f, 0f, 0.3f, 1f)

        orthographicCamera.update()

        if(lastDrop?.let { TimeUtils.timeSinceNanos(it) > DROP_DELAY } != false) spawnRaindrop()

        with(game){
        batch.projectionMatrix.set(orthographicCamera.combined)
        batch.begin()
        batch.draw(bucketImage, bucket.x, bucket.y)
        for (drop in rainDrops){
            batch.draw(dropImage, drop.x, drop.y)
        }
        font.draw(batch, "${(1/ Gdx.graphics.deltaTime).toInt()}", 700f, 400f)
        batch.end()

        for (drop in rainDrops) {
            drop.y -= 200 * Gdx.graphics.deltaTime
            if (drop.y + 64 < 0) rainDrops.removeValue(drop, false)
            if (drop.overlaps(bucket)){
                dropSound.play()
                rainDrops.removeValue(drop, true)
            }
        }
        moveRectangle(bucket)
        }
    }

    private fun spawnRaindrop() {
        Rectangle().apply {
            x = MathUtils.random(0f, 800-64f)
            y = 480f
            width = 64f
            height = 64f
            rainDrops.add(this)
        }
        lastDrop = TimeUtils.nanoTime()
    }

    private fun moveRectangle(item: Rectangle) {
        when {
            Gdx.input.isTouched -> {
                Vector3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f).let {
                    orthographicCamera.unproject(it)
                    item.x = it.x - 64 / 2
                }
            }
            Gdx.input.isKeyPressed(Input.Keys.LEFT) -> item.x -= 200 * Gdx.graphics.deltaTime
            Gdx.input.isKeyPressed(Input.Keys.RIGHT) -> item.x += 200 * Gdx.graphics.deltaTime
        }
    }

    override fun resize(width: Int, height: Int) {}
    override fun pause() {}
    override fun resume() {}
    override fun hide() {}

    override fun dispose() {
        dropImage.dispose()
        bucketImage.dispose()
        rainMusic.dispose()
        dropSound.dispose()
    }



    companion object {
        const val DROP_SOUND = "drop/waterdrop.wav"
        const val RAIN_SOUND = "drop/rain.mp3"
        const val BUCKET_IMG = "drop/bucket.png"
        const val DROP_IMG = "drop/drop.png"
        const val COUNTDOWN_FONT = "countdown.ttf"

        const val DROP_DELAY: Long = 1000000000
    }
}
