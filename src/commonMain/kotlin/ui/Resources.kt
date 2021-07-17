package ui

import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.bitmap.BitmapSlice
import com.soywiz.korim.bitmap.sliceWithSize
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import planet.BiomeType
import tile.SystemType

object Resources {
    var inited = false
    val biomes: MutableMap<String, String> = mutableMapOf()
    private val tiles = mutableMapOf<SystemType, Bitmap>()

    suspend fun init() {
        if (!inited) {
            inited = true
            readBiomes()
            readTiles()
        }
    }

    fun getTileImage(tile: SystemType, size: Int, col: Int = 2, row: Int = 1): BitmapSlice<Bitmap> {
        return tiles[tile]!!.sliceWithSize(col * size, row * size, size, size)
    }

    private suspend fun readBiomes() {
        BiomeType.values().forEach {
            val name = it.fileName
            biomes[name] = resourcesVfs["biomes/$name.json"].readString()
        }
    }

    private suspend fun readTiles() {
        SystemType.values().forEach {
            val name = it.name.lowercase()
            tiles[it] = resourcesVfs["images/tiles/$name.png"].readBitmap()
        }
    }
}