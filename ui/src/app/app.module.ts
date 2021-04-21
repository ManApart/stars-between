import { BrowserModule } from '@angular/platform-browser'
import { NgModule } from '@angular/core'

import { AppRoutingModule } from './app-routing.module'
import { AppComponent } from './app.component'
import { GameViewComponent } from './ship-view/game-view/game-view.component'
import { MainPageComponent } from './ship-view/main-page/main-page.component'
import { TileComponent } from './ship-view/tile/tile.component'
import { BuildToolkitComponent } from './ship-view/build-toolkit/build-toolkit.component'
import { FloorPlanService } from './ship-view/floorPlanService'
import { ShieldService } from './ship-view/shieldService'
import { GameService } from './gameService'
import { PlanetService } from './planetService'
import { HttpClientModule } from '@angular/common/http';
import { SystemMenuComponent } from './ship-view/system-menu/system-menu.component';
import { ModeSelectComponent } from './ship-view/mode-select/mode-select.component';
import { ShieldMenuComponent } from './ship-view/shield-menu/shield-menu.component';
import { ShieldPanelComponent } from './ship-view/shield-panel/shield-panel.component';
import { PlanetMainComponent } from './planet-view/planet-main/planet-main.component'

@NgModule({
  declarations: [
    AppComponent,
    GameViewComponent,
    MainPageComponent,
    TileComponent,
    BuildToolkitComponent,
    SystemMenuComponent,
    ModeSelectComponent,
    ShieldMenuComponent,
    ShieldPanelComponent,
    PlanetMainComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    FloorPlanService,
    GameService,
    PlanetService,
    ShieldService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
