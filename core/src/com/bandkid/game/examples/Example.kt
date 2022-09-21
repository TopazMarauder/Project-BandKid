package com.bandkid.game.examples

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.TimeUtils

class Example: ApplicationAdapter() {
        private lateinit var batch: SpriteBatch
        private lateinit var dropImage: Texture
        private lateinit var bucketImage: Texture
        private lateinit var rainMusic: Music
        private lateinit var dropSound: Sound

        private lateinit var camera: OrthographicCamera

        private lateinit var bucket: Rectangle
        private lateinit var raindrops: Array<Rectangle>
        private var lastDrop: Long? = null

        private lateinit var font: BitmapFont

        override fun create() {
            batch = SpriteBatch()


            font = FreeTypeFontGenerator(Gdx.files.internal(COUNTDOWN_FONT)).let {
                it.generateFont(FreeTypeFontGenerator.FreeTypeFontParameter().apply { size = 20 }).apply { it.dispose() }
            }

            dropImage = Texture(DROP_IMG)
            bucketImage = Texture(BUCKET_IMG)

            dropSound = Gdx.audio.newSound(Gdx.files.internal(DROP_SOUND))
            rainMusic = Gdx.audio.newMusic(Gdx.files.internal(RAIN_SOUND))

            rainMusic.isLooping = true
            rainMusic.play()

            raindrops = Array()

            camera = OrthographicCamera()
            camera.setToOrtho(false, 800F, 480F)

            bucket = Rectangle().apply {
                x = 800f / 2f - 64f / 2f
                y = 20f
                width = 64f
                height = 64f
            }

        }

        override fun render() {
            ScreenUtils.clear(0f, 0f, 0.3f, 1f)

            camera.update()

            if(lastDrop?.let { TimeUtils.timeSinceNanos(it) > DROP_DELAY } != false) spawnRaindrop()

            batch.projectionMatrix.set(camera.combined)
            batch.begin()
            batch.draw(bucketImage, bucket.x, bucket.y)
            for (drop in raindrops){
                batch.draw(dropImage, drop.x, drop.y)
            }
            font.draw(batch, "${(1/ Gdx.graphics.deltaTime).toInt()}", 700f, 400f)
            batch.end()

            for (drop in raindrops) {
                drop.y -= 200 * Gdx.graphics.deltaTime
                if (drop.y + 64 < 0) raindrops.removeValue(drop, false)
                if (drop.overlaps(bucket)){
                    dropSound.play()
                    raindrops.removeValue(drop, true)
                }
            }
            moveRectangle(bucket)
        }

        private fun spawnRaindrop() {
            Rectangle().apply {
                x = MathUtils.random(0f, 800-64f)
                y = 480f
                width = 64f
                height = 64f
                raindrops.add(this)
            }
            lastDrop = TimeUtils.nanoTime()
        }

        private fun moveRectangle(item: Rectangle) {
            when {
                Gdx.input.isTouched -> {
                    Vector3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f).let {
                        camera.unproject(it)
                        item.x = it.x - 64 / 2
                    }
                }
                Gdx.input.isKeyPressed(Input.Keys.LEFT) -> item.x -= 200 * Gdx.graphics.deltaTime
                Gdx.input.isKeyPressed(Input.Keys.RIGHT) -> item.x += 200 * Gdx.graphics.deltaTime
            }
        }

        override fun dispose() {
            batch.dispose()
            dropImage.dispose()
            bucketImage.dispose()
            rainMusic.dispose()
            dropSound.dispose()
            font.dispose()
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