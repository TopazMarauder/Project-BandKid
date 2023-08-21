package com.bandkid.game.utils

import ktx.app.gdxError
import kotlin.math.absoluteValue
import kotlin.random.Random

object SeedManager  {
    private var gameSeed: Int? = null

    fun hasSeed(): Boolean = gameSeed?.let { true } ?: false

    fun clearSeed() { gameSeed = null}

    fun getSeed(setSeed: Int? = null): Int =
        ( gameSeed ?: setSeed?.absoluteValue ?: Random.nextInt().absoluteValue).also {gameSeed = it}

    //region getInt
    fun getInt(min: Int, max: Int): Int =
        gameSeed?.let { Random(it).nextInt(min, max) }
            ?: gdxError("Uninitialized Seed getInt", UninitializedValueException("Uninitialized Seed getInt"))

    fun getInt( max: Int): Int =
        gameSeed?.let { Random(it).nextInt(max) }
            ?: gdxError("Uninitialized Seed getInt", UninitializedValueException("Uninitialized Seed getInt"))

    fun getInt(): Int =
        gameSeed?.let { Random(it).nextInt() }
            ?: gdxError("Uninitialized Seed getInt", UninitializedValueException("Uninitialized Seed getInt"))
    //endregion getInt

    //region getDouble
    fun getDouble(min: Double, max: Double): Double =
        gameSeed?.let { Random(it).nextDouble(min, max) }
            ?: gdxError("Uninitialized Seed getDouble", UninitializedValueException("Uninitialized Seed getDouble"))

    fun getDouble( max: Double): Double =
        gameSeed?.let { Random(it).nextDouble(max) }
            ?: gdxError("Uninitialized Seed getDouble", UninitializedValueException("Uninitialized Seed getDouble"))

    fun getDouble(): Double =
        gameSeed?.let { Random(it).nextDouble() }
            ?: gdxError("Uninitialized Seed getDouble", UninitializedValueException("Uninitialized Seed getDouble"))
    //endregion getDouble

}