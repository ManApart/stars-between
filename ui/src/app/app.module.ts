import { BrowserModule } from '@angular/platform-browser'
import { NgModule } from '@angular/core'

import { AppRoutingModule } from './app-routing.module'
import { AppComponent } from './app.component'
import { GameViewComponent } from './game-view/game-view.component'
import { MainPageComponent } from './main-page/main-page.component'
import { TileComponent } from './tile/tile.component'
import { BuildToolkitComponent } from './build-toolkit/build-toolkit.component'
import { FloorPlanService } from './floorPlanService'
import { GameService } from './gameService'
import { HttpClientModule } from '@angular/common/http';
import { SystemMenuComponent } from './system-menu/system-menu.component';
import { ModeSelectComponent } from './mode-select/mode-select.component'

@NgModule({
  declarations: [
    AppComponent,
    GameViewComponent,
    MainPageComponent,
    TileComponent,
    BuildToolkitComponent,
    SystemMenuComponent,
    ModeSelectComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    FloorPlanService,
    GameService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
