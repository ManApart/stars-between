package org.rak.starsBetween.tile

enum class SystemType {
    ENGINE,
    FLOOR,
    SHIELD,
    SPACE,
    VENT,
    VOID,
    WALL,
    WIRE_FLOOR,
    WIRE_WALL;

    fun isPowerType() : Boolean {
        return this == ENGINE || this == WIRE_FLOOR || this == WIRE_WALL
    }

}
