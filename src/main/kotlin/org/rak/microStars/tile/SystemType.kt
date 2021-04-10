package org.rak.microStars.tile

enum class SystemType {
    ENGINE,
    FLOOR,
    SPACE,
    VENT,
    WALL,
    WIRE_FLOOR,
    WIRE_WALL,
    VOID;

    fun isPowerType() : Boolean {
        return this == ENGINE || this == WIRE_FLOOR || this == WIRE_WALL
    }

}
