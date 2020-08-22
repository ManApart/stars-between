enum class Adjacency(val getRotation: (Boolean, Boolean, Boolean, Boolean) -> Int) {
    NONE(randomRotation),
    ONE_SIDE(oneSideRotation),
    TWO_SIDE(twoSideRotation),
    CORNER(cornerRotation),
    THREE_SIDE(threeSideRotation),
    ALL(randomRotation);

}

private val noRotation = fun(_: Boolean, _: Boolean, _: Boolean, _: Boolean): Int {
    return 0
}

private val randomRotation = fun(_: Boolean, _: Boolean, _: Boolean, _: Boolean): Int {
    return when ((0..4).random()) {
        0 -> 90
        1 -> 180
        2 -> 270
        else -> 0
    }
}

private val oneSideRotation = fun(up: Boolean, down: Boolean, left: Boolean, right: Boolean): Int {
    return when {
        left -> 90
        up -> 180
        right -> 270
        else -> 0
    }
}

private val twoSideRotation = fun(up: Boolean, down: Boolean, left: Boolean, right: Boolean): Int {
    return when {
        right || left -> 0
        else -> 90
    }
}

private val cornerRotation = fun(up: Boolean, down: Boolean, left: Boolean, right: Boolean): Int {
    return when {
        left && up -> 90
        up && right -> 180
        right && down -> 270
        else -> 0
    }
}

private val threeSideRotation = fun(up: Boolean, down: Boolean, left: Boolean, right: Boolean): Int {
    return when {
        !right -> 90
        !down -> 180
        !left -> 270
        else -> 0
    }
}



