package org.rak.microStars

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.Assert.assertEquals
import org.junit.Test
import org.rak.microStars.floorplan.Position
import org.rak.microStars.power.Engine
import org.rak.microStars.tile.Tile
import org.rak.microStars.views.persistence.PersistedTile

class PersistenceTest {
    private val mapper = jacksonObjectMapper()

    @Test
    fun jacksonParseInterface() {
        val tile = Tile(Position(1,2), Engine())
        tile.system.health = 43
        val prePersisted = PersistedTile(tile)
        val json = mapper.writeValueAsString(prePersisted)

        val postPersisted: PersistedTile = mapper.readValue(json)
        val postTile = postPersisted.toTile()

        assertEquals(tile.position, postTile.position)
        assertEquals(tile.system.health, postTile.system.health)
    }

}
