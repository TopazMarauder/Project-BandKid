package com.bandkid.game.models

data class Party(
    var orchestra: Array<Symphonist>,
    var currentSize: Int,
    var maxSize: Int
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Party

        if (!orchestra.contentEquals(other.orchestra)) return false

        return true
    }

    override fun hashCode(): Int {
        return orchestra.contentHashCode()
    }
}
