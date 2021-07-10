package persistence

import floorplan.FloorPlan
import tile.orient

class PersistedFloorPlan(val tiles: List<List<PersistedTile>>) {

    constructor(floorPlan: FloorPlan) : this((0 until floorPlan.size).map { y ->
        (0 until floorPlan.size).map { x ->
            PersistedTile(floorPlan.getTile(x, y))
        }
    })

    fun toFloorPlan(): FloorPlan {
        val size = (tiles.flatten().maxByOrNull { it.x }?.x ?: 9) + 1
        val floorPlan = FloorPlan(size)
        tiles.flatten().map {
            floorPlan.setTileWithoutUpdates(it.toTile(), it.x, it.y)
        }
        floorPlan.getAllTiles().forEach { orient(it, floorPlan) }
        floorPlan.updateAreas()
        floorPlan.updateSystemsById()
        floorPlan.notifySystemsThatFloorplanChanged()

        return floorPlan
    }

}
