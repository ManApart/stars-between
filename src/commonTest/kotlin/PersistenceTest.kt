import floorplan.Position
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import persistence.PersistedTile
import power.Engine
import tile.Tile
import kotlin.test.Test
import kotlin.test.assertEquals

class PersistenceTest {

    @Test
    fun jacksonParseInterface() {
        val tile = Tile(Position(1,2), Engine())
        tile.system.health = 43
        val prePersisted = PersistedTile(tile)
        val json: String = Json.encodeToString(prePersisted)

        val postPersisted: PersistedTile = Json.decodeFromString(json)
        val postTile = postPersisted.toTile()

        assertEquals(tile.position, postTile.position)
        assertEquals(tile.system.health, postTile.system.health)
    }

}
