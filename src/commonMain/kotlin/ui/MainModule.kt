package ui

import com.soywiz.korge.scene.Module
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.*
import ui.planetScene.PlanetScene
import ui.shipScene.ShipScene

const val WINDOW_SIZE = 640

object MainModule : Module() {
    override val mainScene = PlanetScene::class
//    override val mainScene = ShipScene::class
    override val title: String = "Stars Between"
    override val size: SizeInt = SizeInt(Size(WINDOW_SIZE, WINDOW_SIZE))
    override val windowSize = size
    override val icon: String = "images/tiles/space.png"
    override val scaleMode: ScaleMode = ScaleMode.NO_SCALE
    override val clipBorders: Boolean = false
    override val scaleAnchor: Anchor = Anchor.TOP_LEFT

    override suspend fun AsyncInjector.configure() {
        mapPrototype { PlanetScene() }
        mapPrototype { ShipScene() }
    }
}