class SimpleTile(tile: Tile) {
    val name = tile.name
    val solid = tile.isSolid()
    val air = tile.air
    val health = tile.health
    val adjacency = tile.adjacency
    val rotation = tile.rotation
}