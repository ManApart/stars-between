package org.rak.microStars.power

import org.rak.microStars.floorplan.Area
import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.tile.Tile
import org.rak.microStars.tile.TileType
import kotlin.math.max
import kotlin.math.min

fun simulatePower(floorPlan: FloorPlan) {
//    val powerTiles = floorPlan.getAllTiles().filter { it.totalPowerCapacity > 0 }
//
//    if (powerTiles.isEmpty()) {
//        return
//    }

    floorPlan.powerAreas.areas.forEach { area ->
        distributeEnginePower(area)
        distributeWirePower(area)
        clampPower(area)
    }
}

private fun distributeEnginePower(area: Area) {
    val engines = area.tiles.filter { it.type == TileType.ENGINE }
    engines.forEach { engine ->
        val neighbors = area.getNeighbors(engine).filter { it.power < it.totalPowerCapacity }
        neighbors.forEach {
            val need = it.totalPowerCapacity - it.power
            val given = min(engine.power, need)
            engine.power -= given
            it.power += given
        }
    }
}

private fun distributeWirePower(area: Area) {
    val wires = area.tiles.filter { it.type == TileType.WIRE_WALL || it.type == TileType.WIRE_FLOOR && it.power > 0 }
    wires.forEach { wire ->
        val neighbors = area.getNeighbors(wire).filter { it != wire.lastReceivedPowerFrom }
        val greatestNeed = neighbors.maxByOrNull { it.totalPowerCapacity - it.power }
        if (greatestNeed != null) {
            val need = greatestNeed.totalPowerCapacity - greatestNeed.power
            val given = min(need, wire.power)
            greatestNeed.power += given
            wire.power -= given
            greatestNeed.lastReceivedPowerFrom = wire
        }
    }
}

//    val open = powerTiles.filter { it.type == TileType.VENT }.toMutableList()
//    val closed = mutableListOf<Tile>()
//
//    while (open.isNotEmpty()) {
//        val current = open.first()
//        open.remove(current)
//        if (current.totalPowerCapacity > 0 && !closed.contains(current)) {
//            val neighbors =
//                floorPlan.getNeighbors(current).filter { it.totalPowerCapacity > 0 && !closed.contains(it) }
//            pullPower(current, neighbors)
//
//            open.addAll(neighbors)
//        }
//        closed.add(current)
//    }

private fun pullPower(source: Tile, others: List<Tile>) {
    //if multiple have same low power, user more distance from source?
    val winner = others.maxByOrNull { it.power }
    if (winner != null) {
        val need = source.totalPowerCapacity - source.power
        val given = min(need, winner.power)
        source.power += given
        winner.power -= given
    }

}

private fun clampPower(area: Area) {
    area.tiles.forEach {
        it.power = min(it.totalPowerCapacity, max(0, it.power))
    }
}