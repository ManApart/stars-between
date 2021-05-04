package org.rak.starsBetween.floorplan

import org.rak.starsBetween.power.Powerable
import org.rak.starsBetween.shipStructor.SPACE_SYSTEM
import org.rak.starsBetween.systems.ShipSystem
import org.rak.starsBetween.tile.DEFAULT_TILE
import org.rak.starsBetween.tile.SystemType
import org.rak.starsBetween.tile.Tile
import org.rak.starsBetween.tile.orient

class FloorPlan(val size: Int = 5) {
    private val tiles = (0 until size).associate { y ->
        (y to (0 until size).associateWith { x ->
            Tile(Position(x, y), SPACE_SYSTEM)
        }.toMutableMap())
    }

    private var selectedTile = getAllTiles().first()

    var airAreas = AreaGroup(this) { !it.system.isSolid() }
    var powerAreas = AreaGroup(this) { it.system.type.isPowerType() || it.system is Powerable }

    private var systemsById = mapOf<SystemType, Map<Int, Tile>>()

    fun getTileMap(): Map<Int, Map<Int, Tile>> {
        return tiles.toMap()
    }

    fun getAllTiles(): List<Tile> {
        return tiles.values.flatMap { it.values }
    }

    fun getTile(pos: Position): Tile {
        return getTile(pos.x, pos.y)
    }

    fun getTile(x: Int, y: Int): Tile {
        return if (x in 0 until size && y in 0 until size) {
            tiles[y]!![x] ?: DEFAULT_TILE
        } else {
            DEFAULT_TILE
        }
    }

    fun setTile(tile: Tile, pos: Position) {
        setTile(tile, pos.x, pos.y)
    }

    fun setTile(tile: Tile, x: Int, y: Int) {
        if (x in 0 until size && y in 0 until size) {
            val newTile = tile.copy(position = Position(x, y))
            tiles[y]!![x] = newTile
            orient(newTile, this)
            getNeighbors(newTile).forEach { orient(it, this) }
            updateAreas()
            updateSystemsById()
            notifySystemsThatFloorplanChanged()
        }
    }

    fun setTileWithoutUpdates(tile: Tile, pos: Position) {
        setTileWithoutUpdates(tile, pos.x, pos.y)
    }

    fun setTileWithoutUpdates(tile: Tile, x: Int, y: Int) {
        if (x in 0 until size && y in 0 until size) {
            val newTile = tile.copy(position = Position(x, y))
            tiles[y]!![x] = newTile
        }
    }

    fun getId(system: ShipSystem) : Int {
        return systemsById[system.type]!!.entries.firstOrNull { it.value.system == system }?.key ?: 0
    }

    fun getSystem(type: SystemType, id: Int): Tile? {
        return systemsById[type]!![id]
    }

    fun getSystems(type: SystemType): Map<Int, Tile> {
        return systemsById[type]!!
    }

    fun getNeighbors(x: Int, y: Int): List<Tile> {
        return getNeighbors(getTile(x, y))
    }

    fun getNeighbors(tile: Tile): List<Tile> {
        return tile.position.neighbors().map { getTile(it) }.filter { it != DEFAULT_TILE }
    }

    //TODO
    fun getNeighbors(tile: Tile, radius: Int = 1): List<Tile> {
        return getNeighbors(tile)
    }

    fun updateAreas() {
        airAreas = AreaGroup(this) { !it.system.isSolid() }
        powerAreas = AreaGroup(this) { it.system.type.isPowerType() || it.system is Powerable }
        setSelectedTile(selectedTile)
    }

    fun setSelectedTile(selectedTile: Tile) {
        this.selectedTile = selectedTile
        getAllTiles().forEach { tile ->
            tile.distanceFromSelected = selectedTile.distanceMap.getDistance(tile)
        }
    }

    fun updateSystemsById() {
        val map = SystemType.values().associate { it to mutableMapOf<Int, Tile>() }
        getAllTiles().forEach {
            val systemMap = map[it.system.type]!!
            val id = (systemMap.keys.maxOrNull() ?: 0) + 1
            systemMap[id] = it
        }
        systemsById = map
    }

    private fun notifySystemsThatFloorplanChanged() {
        getAllTiles().forEach { it.system.floorPlanUpdated(this, it) }
    }

}


