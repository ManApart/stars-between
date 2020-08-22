class SimpleFloorPlan(floorPlan: FloorPlan) {
    val tiles = floorPlan.getTileMap().values.map { row -> row.values.map { column -> SimpleTile(column) }.toList() }
}