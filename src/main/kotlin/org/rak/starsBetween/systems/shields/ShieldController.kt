package org.rak.starsBetween.systems.shields

import org.rak.starsBetween.game.Game
import org.rak.starsBetween.tile.SystemType
import org.rak.starsBetween.views.shieldView.SimpleShieldBase
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/shield")
class ShieldController {
    @GetMapping
    fun getShields(): List<SimpleShieldBase> {
        return Game.floorPlan.getSystems(SystemType.SHIELD).entries.map { (id, tile) ->
            val shield = tile.system as Shield
            SimpleShieldBase(shield, id, tile.position.x, tile.position.y)
        }
    }

    @PutMapping
    fun updateShield(@RequestBody shieldControls: ShieldControlUpdate): List<SimpleShieldBase> {
        val shield = Game.floorPlan.getSystem(SystemType.SHIELD, shieldControls.id)?.system as Shield?
        if (shield == null) {
            println("Could not find shield with id $shieldControls.id")
        } else {
            shield.frequency = shieldControls.frequency
            shield.currentDesiredPower = shieldControls.currentDesiredPower
        }
        return getShields()
    }

}
