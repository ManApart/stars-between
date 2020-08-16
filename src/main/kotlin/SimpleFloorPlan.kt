class SimpleFloorPlan(floorPlan: FloorPlan) {
    val tiles = floorPlan.tiles.values.map { row -> row.values.map { column -> SimpleTile(column) }.toList() }
}