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
    private val images = mutableMapOf<String, Bitmap>()

    suspend fun init() {
        if (!inited) {
            inited = true
            readBiomes()
        }
    }

    suspend fun getTileImage(tile: SystemType, size: Int, col: Int, row: Int): BitmapSlice<Bitmap> {
        return getImage("images/tiles/${tile.name.lowercase()}.png").sliceWithSize(col * size, row * size, size, size)
    }

    suspend fun getImage(path: String): Bitmap {
        return images.getOrPut(path) {
            resourcesVfs[path].readBitmap()
        }
    }

    private suspend fun readBiomes() {
        BiomeType.values().forEach {
            val name = it.fileName
            biomes[name] = resourcesVfs["biomes/$name.json"].readString()
        }
    }
}