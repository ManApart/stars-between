package org.rak.microStars.power

import org.rak.microStars.floorplan.Area
import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.tile.Tile
import org.rak.microStars.tile.SystemType
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
    val engines = area.tiles.filter { it.system.type == SystemType.ENGINE }
    engines.forEach { engineTile ->
        val engine = engineTile.system as Engine
        val neighbors = area.getNeighbors(engineTile).filter { it.system is Powerable && it.system.power < it.system.totalPowerCapacity }
        neighbors.forEach {
            val system = it.system as Powerable
            val need = system.totalPowerCapacity - system.power
            val given = min(engine.power, need)
            engine.power -= given
            it.system.power += given
        }
    }
}

private fun distributeWirePower(area: Area) {
    val wires = area.tiles.filter { it.system is Wire && it.system.power > 0 }
    wires.forEach { wireTile ->
        val wire = wireTile.system as Wire
        val neighbors = area.getNeighbors(wireTile).filter { it != wire.lastReceivedPowerFrom && it.system is Powerable }.map { it.system as Powerable }
        val greatestNeed = neighbors.maxByOrNull { it.totalPowerCapacity - it.power }
        if (greatestNeed != null) {
            val need = greatestNeed.totalPowerCapacity - greatestNeed.power
            val given = min(need, wire.power)
            greatestNeed.power += given
            wire.power -= given
            greatestNeed.lastReceivedPowerFrom = wireTile
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

//private fun pullPower(source: Tile, others: List<Tile>) {
//    //if multiple have same low power, user more distance from source?
//    val winner = others.maxByOrNull { it.power }
//    if (winner != null) {
//        val need = source.totalPowerCapacity - source.power
//        val given = min(need, winner.power)
//        source.power += given
//        winner.power -= given
//    }
//
//}

private fun clampPower(area: Area) {
    area.tiles.filter { it.system is Powerable }.map { it.system as Powerable }.forEach {
        it.power = min(it.totalPowerCapacity, max(0, it.power))
    }
}
