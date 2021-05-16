package org.rak.starsBetween.views.crewView

import org.rak.starsBetween.crew.CrewMan
import org.rak.starsBetween.crew.Division

class CrewManUpdate(val id: Int, val division: Division) {

    constructor(crewMan: CrewMan) : this(
        crewMan.id,
        crewMan.division
    )
}